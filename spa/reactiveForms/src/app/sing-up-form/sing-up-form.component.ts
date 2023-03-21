import {Component} from '@angular/core';
import { FormControl, FormGroup, Validators} from "@angular/forms";
import {RequestService} from "./request.service";

@Component({
  selector: 'app-sing-up-form',
  templateUrl: './sing-up-form.component.html',
  styleUrls: ['./sing-up-form.component.css']
})
export class SingUpFormComponent {

  signupForm: FormGroup
  isLoginMode: boolean = false
  constructor(private reqService: RequestService) {
  }

  ngOnInit(){
    this.signupForm = new FormGroup({
      'userData': new FormGroup({
        'username': new FormControl(null, Validators.required),
        'email': new FormControl(null, Validators.required),
        'password': new FormControl(null, Validators.required)
      }),
    })
  }


  onSubmit(){
    const username = this.signupForm.value.userData.username
    const email = this.signupForm.value.userData.email
    const password = this.signupForm.value.userData.password

    if (this.isLoginMode){
      this.reqService.onSingIn(email, password).subscribe(res => {
        // @ts-ignore
        localStorage.setItem('token', res.token)
      })
    } else {
      this.reqService.onSingUp(email, password, username).subscribe(res => {
        // @ts-ignore
        localStorage.setItem('token', res.token)
      })
    }
  }

  authGoogle(){
    this.reqService.onGoogleSignIn().subscribe(res =>{
      // @ts-ignore
      localStorage.setItem('token', res.token)
    })
  }


  SwitchMode(){
    this.isLoginMode = !this.isLoginMode;
  }


}

