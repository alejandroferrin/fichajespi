import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpXsrfTokenExtractor, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenService } from '../service/token.service';


@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {

  constructor(private tokenService: TokenService) { }

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {

    if (this.tokenService.isExpired()) {
      this.tokenService.logOut();
      return next.handle(req);
    } else {
      let interceptedReq = req
      const token = this.tokenService.getToken()
      if (token != null) {
        interceptedReq = req
          .clone({
            headers: req.headers
              .set('Authorization', 'Bearer ' + token)
          })
      }
      return next.handle(interceptedReq)
    }
  }
}

export const interceptorProvider = [{ provide: HTTP_INTERCEPTORS, useClass: InterceptorService, multi: true }]
