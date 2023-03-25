import {HttpEventType, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {tap} from "rxjs";


export class AuthInterceptorService implements HttpInterceptor{

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const token = localStorage.getItem('token')
    console.log(token)
    if (!!token){
      const modifiedReq = req.clone({headers: req.headers.set('Authorization', 'Bearer ' + String(token))})
      console.log(modifiedReq.headers)
      console.log('interceptor works!')
      return next.handle(modifiedReq)
    }
    console.log('base req')
    return next.handle(req)
  }
}
