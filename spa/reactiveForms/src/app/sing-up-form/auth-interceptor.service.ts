import {HttpEventType, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {tap} from "rxjs";


export class AuthInterceptorService implements HttpInterceptor{
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const token = JSON.parse(localStorage.getItem('token') || '{}');
    const modifiedReq = req.clone({headers: req.headers.append('Authorization', token)})
    console.log('interceptor works!')
    return next.handle(modifiedReq).pipe(tap(event => {
      console.log(event)
      if (event.type === HttpEventType.Response){
        console.log('response arrived!!!, body data:')
        console.log(event.body);
      }
    }))
  }
}
