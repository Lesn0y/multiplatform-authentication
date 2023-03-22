import {Component, OnInit} from '@angular/core';
import {RequestService} from "../sing-up-form/request.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit{

  username: string
  constructor(private reqService: RequestService, private router: Router) {
  }

  ngOnInit() {
    this.reqService.getUsername().subscribe(res => console.log(res))
  }

  logout(){
    localStorage.clear()
    this.router.navigateByUrl('signUp-page')
  }
}
