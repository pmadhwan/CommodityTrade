import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor( private route : Router) { }

  ngOnInit() {
  }

  signout(){
    console.log('In SignOut');
    localStorage.clear();
    this.route.navigate(['/seller-home']);
  }

}
