import { AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { UserService } from './service/user.service';
import { User } from './model/user.interface';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements AfterViewInit {

  @ViewChild('userInput')
  userInput: ElementRef;

  userName = new FormControl('');

  user: User;

  constructor(private userService: UserService) {
  }

  ngAfterViewInit(): void {
    setTimeout(() => this.userInput.nativeElement.focus());
  }

  activateUser() {
    if (this.userName.value && this.userName.value !== '') {
      this.userService.getUser(this.userName.value)
        .then(user => this.user = user);
    }
  }
}
