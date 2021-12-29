import {
  AfterViewChecked,
  AfterViewInit,
  Component,
  ElementRef,
  Input,
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
import Swal from 'sweetalert2';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.scss']
})
export class MessagesComponent implements OnInit, OnDestroy, AfterViewInit, AfterViewChecked {

  constructor(private messageService: MessageService) {
  }

  @Input() user: User;

  @ViewChild('scroll') private scrollMessage: ElementRef;

  @ViewChild('messageInput') messageInput: ElementRef;

  messages: Message[] = [];
  name = new FormControl('');
  websocket: any;
  scrollTop = 200;
  loginDate: Date;
  logoutDate: Date;
  count : number;
  switch = true;
  userStatus = false;
  onlineUserNames: User[];
  tempUser : User;

  public textArea: string = '';
  public isEmojiPickerVisible: boolean;

  ngOnInit(): void {
    this.switch = true;
    this.connect();
    this._getMessages();
    this._setScrollToBottom();
    MessagesComponent._loadListener();
    this.setLogDate();
    console.log(this.messages);
  }

  ngAfterViewInit(): void {
    setTimeout(() => this.messageInput.nativeElement.focus());
  }

  ngOnDestroy(): void {
    this.disconnect();
  }

  onActivate() {
    window.scroll(0, 0);
  }

  ngAfterViewChecked(): void {
    this._setScrollToBottom();
  }

  public addEmoji(event) {
    this.textArea = `${this.textArea}${event.emoji.native}`;
    // this.isEmojiPickerVisible = false;
  }

  setLogDate() {
    this.loginDate = new Date(this.user.loginTime);
    this.logoutDate = new Date(this.user.exitTime);
  }

  connect() {
    const socket = new WebSocket(environment.webSocket);
    this.websocket = Stomp.over(socket);
    this.websocket.connect({}, () => {
      this.websocket.subscribe('/chat', (frame: { body: string }) => {
        const message: Message = JSON.parse(frame.body);
        this.messages.push({...message, date: new Date(message.date)});
      });
    }, (error) => {
      alert('STOMP error ' + error);
    });
  }

  disconnect() {
    if (this.websocket != null) {
      this.websocket.websocket.close();
    }
    console.warn('Disconnected');
  }

  send() {
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
    return userId === this.user.id;
  }

  private static _loadListener() {
    window.addEventListener('beforeunload', function (e) {
      const confirmationMessage = '\o/';
      e.returnValue = confirmationMessage;     // Gecko, Trident, Chrome 34+
      return confirmationMessage;              // Gecko, WebKit, Chrome <34
    });
  }

  private _getMessages() {
    this.messageService.getMessages().then((messages: Message[]) => {
      this.messages = messages;
      this._unreadMessagesCount();
    });
  }

  private _unreadMessagesCount() {
    this.count = this.messages.filter(time => (this.user.exitTime < time.date.getTime())).filter(tim => tim.date < this.loginDate).length;
    Swal.fire({
      title: 'You have ' + this.count + ' new messages.',
      timer: 2000,
      timerProgressBar: true,
      backdrop: `rgba(1, 1, 1, 0.8)`,
      color: "#2d6ad9",
      confirmButtonColor: "#2d6ad9",
    });
  }

  private _setScrollToBottom() {
    try {
      this.scrollMessage.nativeElement.scrollTop = this.scrollMessage.nativeElement.scrollHeight;
    } catch (e) {
    }
  }

}
