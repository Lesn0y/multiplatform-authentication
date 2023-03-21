import {Injectable} from "@angular/core";
import {HttpClient, HttpRequest} from "@angular/common/http";

@Injectable({providedIn: 'root'})
export class RequestService{
  constructor(private httpReq: HttpClient) {
  }

  onSingUp( email: string, password: string, username?: string,){
    return this.httpReq.post('http://localhost:8080/auth/register', {
      email: email,
      username: username,
      password: password
    })
  }

  onSingIn( email: string, password: string, username?: string,){
    return this.httpReq.post('http://localhost:8080/auth/login', {
      email: email,
      username: username,
      password: password
    })
  }


  onGoogleSignIn(){
    return this.httpReq.get('http://localhost:8080/oauth2/google')
  }

  onVkSignIn(){
    return this.httpReq.get('http://localhost:8080/oauth/vk')
  }

}
