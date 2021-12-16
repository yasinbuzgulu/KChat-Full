import {
  AfterViewChecked,
  AfterViewInit,
  Component,
  ElementRef,
  Input, OnChanges,
  OnDestroy,
  OnInit,
  ViewChild
} from '@angular/core';
import {FormControl} from '@angular/forms';
import {Stomp} from 'stompjs/lib/stomp.js';
import {MessageService} from '../service/message.service';
import {Message} from '../model/message.interface';
import {User} from '../model/user.interface';
import {environment} from '../../environments/environment';
import {UserService} from '../service/user.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.scss']
})
export class MessagesComponent implements OnInit, OnDestroy, AfterViewInit, AfterViewChecked {

  constructor(private messageService: MessageService,
              private userService: UserService) {
  }

  @Input()
  user: User;

  @ViewChild('scroll') private scrollMessage: ElementRef;

  @ViewChild('messageInput')
  messageInput: ElementRef;

  messages: Message[] = [];
  name = new FormControl('');
  ws: any;
  scrollTop = 200;
  loginDate: Date;
  logoutDate: Date;
  count ;
  switch = true;
  isOnline = false;

  ngOnInit(): void {
    this.switch = true;
    // this._findUser();
    this.connect();
    this._getMessages();
    this._setScrollToBottom();
    window.addEventListener('beforeunload', function(e) {
      const confirmationMessage = '\o/';
      e.returnValue = confirmationMessage;     // Gecko, Trident, Chrome 34+
      return confirmationMessage;              // Gecko, WebKit, Chrome <34
    });
    this.loginDate = new Date(this.user.loginTime);
    this.logoutDate = new Date(this.user.exitTime);
    // this._unreadMessagesCount();

  }

  ngAfterViewInit(): void {
    setTimeout(() => this.messageInput.nativeElement.focus());
  }

  ngOnDestroy(): void {
    this.disconnect();
  }

  private _getMessages() {
    this.messageService.getMessages().then((messages: Message[]) => {
      this.messages = messages;
      this._unreadMessagesCount();
    });
  }

  private _unreadMessagesCount() {
    if (!this.isOnline ){
    this.count = this.messages.filter(time => ( this.user.exitTime < time.date.getTime())).filter(tim => tim.date < this.loginDate).length;
  }
  }

  private _findUser() {
    this.userService.getUser(this.user.name).then(user => this.user = user);
  }


  connect() {
    const socket = new WebSocket(environment.webSocket);
    this.ws = Stomp.over(socket);
    this.ws.connect({}, () => {
      this.ws.subscribe('/chat', (frame: { body: string }) => {
        const message: Message = JSON.parse(frame.body);
        this.messages.push({...message, date: new Date(message.date)});
      } );
    }, (error) => {
      alert('STOMP error ' + error);
    });
  }

  disconnect() {
    if (this.ws != null) {
      this.ws.ws.close();
    }
    console.warn('Disconnected');
  }

  private _setScrollToBottom() {
    try {
      this.scrollMessage.nativeElement.scrollTop = this.scrollMessage.nativeElement.scrollHeight;
    } catch (err) {
    }
  }

  send() {
    this._findUser();
    this.switch = false;
    this._setScrollToBottom();
    this.messageService.send({
      text: this.name.value,
      userId: this.user.id
    });
    this.name.setValue('');
  }

  isToday(date: Date) {
    const today = new Date();
    return date.getDate() === today.getDate()
      && date.getMonth() === today.getMonth()
      && date.getFullYear() === today.getFullYear();
  }

  isCurrentUser(userId: string) {
    this.isOnline = true;
    return userId === this.user.id;
  }

  onActivate(event) {
    window.scroll(0, 0);
  }

  ngAfterViewChecked(): void {
    this._setScrollToBottom();
  }
}
