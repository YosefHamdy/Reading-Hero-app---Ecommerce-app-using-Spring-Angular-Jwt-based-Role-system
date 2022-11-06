import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/auth/user.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  userService!:UserService;
  constructor(userService:UserService) {
    this.userService = userService;
   }

  ngOnInit(): void {
  }

}
