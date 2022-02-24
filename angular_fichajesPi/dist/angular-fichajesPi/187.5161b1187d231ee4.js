"use strict";(self.webpackChunkangular_fichajesPi=self.webpackChunkangular_fichajesPi||[]).push([[187],{1187:(Z,g,o)=>{o.r(g),o.d(g,{LandingModule:()=>M});var c=o(8870),l=o(1207);class u{constructor(i,t){this.numero=i,this.password=t}}var n=o(9215),m=o(5410),p=o(3115),a=o(6603);function f(e,i){if(1&e&&(n.TgZ(0,"div",17),n._uU(1),n.qZA()),2&e){const t=n.oxw();n.xp6(1),n.hij("",t.errMsg," ")}}const h=[{path:"",redirectTo:"home",pathMatch:"full"},{path:"home",component:(()=>{class e{constructor(t,r,s){this.tokenService=t,this.authService=r,this.router=s,this.loginUsuario=null,this.numero="",this.password="",this.errMsg=""}ngOnInit(){}onLogin(){this.loginUsuario=new u(this.numero,this.password),this.authService.login(this.loginUsuario).subscribe(t=>{this.tokenService.setToken(t.token),this.router.navigate(["/intranet"])},t=>{this.errMsg=t.error.error,this.clear()})}clear(){this.numero="",this.password=""}}return e.\u0275fac=function(t){return new(t||e)(n.Y36(m.B),n.Y36(p.e),n.Y36(l.F0))},e.\u0275cmp=n.Xpm({type:e,selectors:[["app-landing"]],decls:25,vars:4,consts:[[1,"landing-waves","d-flex","flex-column","justify-content-center"],[1,"container","col-xl-10","col-xxl-8","mx-auto"],[1,"row","align-items-center","g-lg-5"],[1,"col-lg-7","mx-auto","text-center"],["src","../../assets/images/fp_logo_texto-2.svg","alt","","width","600"],[1,"fs-4","text-dark"],[1,"row","align-items-center","g-lg-5","mt-3"],[1,"col-md-10","mx-auto","col-lg-5"],["novalidate","",3,"ngSubmit"],["f","ngForm"],[1,"form-floating","mb-3"],["type","text","id","floatingInput","placeholder","N\xfamero de empleado","required","","name","numero",1,"form-control",3,"ngModel","ngModelChange"],["for","floatingInput"],["type","password","id","floatingPassword","placeholder","Password","required","","name","password",1,"form-control",3,"ngModel","ngModelChange"],["for","floatingPassword"],["type","submit",1,"w-100","btn","btn-lg","bg-pink","text-white",3,"disabled"],["class","alert alert-danger my-3 text-center",4,"ngIf"],[1,"alert","alert-danger","my-3","text-center"]],template:function(t,r){if(1&t&&(n.TgZ(0,"div",0),n.TgZ(1,"div",1),n.TgZ(2,"div",2),n.TgZ(3,"div",3),n._UZ(4,"img",4),n.TgZ(5,"p",5),n._uU(6,"Plataforma de fichajes pensada para funcionar sobre RaspberryPi para continuar "),n.TgZ(7,"b"),n._uU(8,"inicia sesi\xf3n"),n.qZA(),n._uU(9," como usuario o como responsable de rrhh."),n.qZA(),n.qZA(),n.qZA(),n.TgZ(10,"div",6),n.TgZ(11,"div",7),n.TgZ(12,"form",8,9),n.NdJ("ngSubmit",function(){return r.onLogin()}),n.TgZ(14,"div",10),n.TgZ(15,"input",11),n.NdJ("ngModelChange",function(d){return r.numero=d}),n.qZA(),n.TgZ(16,"label",12),n._uU(17,"N\xfamero empleado"),n.qZA(),n.qZA(),n.TgZ(18,"div",10),n.TgZ(19,"input",13),n.NdJ("ngModelChange",function(d){return r.password=d}),n.qZA(),n.TgZ(20,"label",14),n._uU(21,"Password"),n.qZA(),n.qZA(),n.TgZ(22,"button",15),n._uU(23,"Iniciar Sesi\xf3n"),n.qZA(),n.qZA(),n.YNc(24,f,2,1,"div",16),n.qZA(),n.qZA(),n.qZA(),n.qZA()),2&t){const s=n.MAs(13);n.xp6(15),n.Q6J("ngModel",r.numero),n.xp6(4),n.Q6J("ngModel",r.password),n.xp6(3),n.Q6J("disabled",!s.valid),n.xp6(2),n.Q6J("ngIf",s.submitted&&""!=r.errMsg)}},directives:[a._Y,a.JL,a.F,a.Fj,a.Q7,a.JJ,a.On,c.O5],styles:[".landing-waves[_ngcontent-%COMP%]{background-color:#f3f9fa!important;background-image:url(\"data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='100%25' height='100%25' viewBox='0 0 1600 800'%3E%3Cg %3E%3Cpath fill='%23ebf5f7' d='M486 705.8c-109.3-21.8-223.4-32.2-335.3-19.4C99.5 692.1 49 703 0 719.8V800h843.8c-115.9-33.2-230.8-68.1-347.6-92.2C492.8 707.1 489.4 706.5 486 705.8z'/%3E%3Cpath fill='%23e4f1f3' d='M1600 0H0v719.8c49-16.8 99.5-27.8 150.7-33.5c111.9-12.7 226-2.4 335.3 19.4c3.4 0.7 6.8 1.4 10.2 2c116.8 24 231.7 59 347.6 92.2H1600V0z'/%3E%3Cpath fill='%23dceef0' d='M478.4 581c3.2 0.8 6.4 1.7 9.5 2.5c196.2 52.5 388.7 133.5 593.5 176.6c174.2 36.6 349.5 29.2 518.6-10.2V0H0v574.9c52.3-17.6 106.5-27.7 161.1-30.9C268.4 537.4 375.7 554.2 478.4 581z'/%3E%3Cpath fill='%23d4eaed' d='M0 0v429.4c55.6-18.4 113.5-27.3 171.4-27.7c102.8-0.8 203.2 22.7 299.3 54.5c3 1 5.9 2 8.9 3c183.6 62 365.7 146.1 562.4 192.1c186.7 43.7 376.3 34.4 557.9-12.6V0H0z'/%3E%3Cpath fill='%23CDE6E9' d='M181.8 259.4c98.2 6 191.9 35.2 281.3 72.1c2.8 1.1 5.5 2.3 8.3 3.4c171 71.6 342.7 158.5 531.3 207.7c198.8 51.8 403.4 40.8 597.3-14.8V0H0v283.2C59 263.6 120.6 255.7 181.8 259.4z'/%3E%3Cpath fill='%23d7ebed' d='M1600 0H0v136.3c62.3-20.9 127.7-27.5 192.2-19.2c93.6 12.1 180.5 47.7 263.3 89.6c2.6 1.3 5.1 2.6 7.7 3.9c158.4 81.1 319.7 170.9 500.3 223.2c210.5 61 430.8 49 636.6-16.6V0z'/%3E%3Cpath fill='%23e1f0f2' d='M454.9 86.3C600.7 177 751.6 269.3 924.1 325c208.6 67.4 431.3 60.8 637.9-5.3c12.8-4.1 25.4-8.4 38.1-12.9V0H288.1c56 21.3 108.7 50.6 159.7 82C450.2 83.4 452.5 84.9 454.9 86.3z'/%3E%3Cpath fill='%23ebf5f6' d='M1600 0H498c118.1 85.8 243.5 164.5 386.8 216.2c191.8 69.2 400 74.7 595 21.1c40.8-11.2 81.1-25.2 120.3-41.7V0z'/%3E%3Cpath fill='%23f5fafb' d='M1397.5 154.8c47.2-10.6 93.6-25.3 138.6-43.8c21.7-8.9 43-18.8 63.9-29.5V0H643.4c62.9 41.7 129.7 78.2 202.1 107.4C1020.4 178.1 1214.2 196.1 1397.5 154.8z'/%3E%3Cpath fill='%23FFFFFF' d='M1315.3 72.4c75.3-12.6 148.9-37.1 216.8-72.4h-723C966.8 71 1144.7 101 1315.3 72.4z'/%3E%3C/g%3E%3C/svg%3E\")!important;background-attachment:fixed!important;background-size:cover!important;min-height:100vh;padding:0}button[_ngcontent-%COMP%]{border-color:transparent!important}"]}),e})()}];let v=(()=>{class e{}return e.\u0275fac=function(t){return new(t||e)},e.\u0275mod=n.oAB({type:e}),e.\u0275inj=n.cJS({imports:[[l.Bz.forChild(h)],l.Bz]}),e})();var C=o(7729);let M=(()=>{class e{}return e.\u0275fac=function(t){return new(t||e)},e.\u0275mod=n.oAB({type:e}),e.\u0275inj=n.cJS({imports:[[c.ez,a.u5,C.I,v]]}),e})()}}]);