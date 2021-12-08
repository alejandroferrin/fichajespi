import { DebugElement } from "@angular/core";
import { ComponentFixture, TestBed, waitForAsync } from "@angular/core/testing";
import { By } from "@angular/platform-browser";
import { ActivatedRoute, convertToParamMap } from "@angular/router";
import { RouterTestingModule } from '@angular/router/testing';
import { of } from "rxjs";
import { TokenService } from "src/app/core/auth/service/token.service";
import { PERMISOS } from "src/mock/mock.permisos";
import { PermisoService } from "../../service/permiso.service";
import { PermisosSharedModule } from "../permisos-shared.module";
import { PermisosListaComponent } from "./permisos-lista.component";



describe('PermisosListaComponent', () => {

  let fixture: ComponentFixture<PermisosListaComponent>;
  let component: PermisosListaComponent;
  let el: DebugElement;
  let permisoService: any;
  let tokenService: any;

  const permisos = PERMISOS;

  beforeEach(waitForAsync(() => {

    const permisoServiceSpy = jasmine.createSpyObj('PermisoService', ['getElements'])
    const tokenServiceSpy = jasmine.createSpyObj('TokenService', ['isAdmin'])

    let activatedRouteSpy = {
      snapshot: {
        params: convertToParamMap({
          numero: '001',
        })
      }
    };

    TestBed.configureTestingModule({
      imports: [
        PermisosSharedModule,
        RouterTestingModule
      ],
      providers: [
        { provide: PermisoService, useValue: permisoServiceSpy },
        { provide: TokenService, useValue: tokenServiceSpy },
        { provide: ActivatedRoute, useValue: activatedRouteSpy },
      ]
    }).compileComponents()
      .then(() => {
        fixture = TestBed.createComponent(PermisosListaComponent);
        component = fixture.componentInstance;
        el = fixture.debugElement;
        permisoService = TestBed.inject(PermisoService);
        tokenService = TestBed.inject(TokenService);
      });

  }));

  it("should create the component", () => {
    expect(component).toBeTruthy();
  });

  it("should display permisos - not admin user -", () => {

    permisoService.getElements.and.returnValue(of(permisos));
    tokenService.isAdmin.and.returnValue(false);

    fixture.detectChanges();

    const columns = el.queryAll(By.css('th'));
    const rows = el.queryAll(By.css('tr'));

    expect(columns.length).toBe(7, "Unexpected number of columns found");

    expect(rows.length).toBe(16, "Unexpected number of rows found");

  });

  it("should display permisos - admin user -", () => {

    permisoService.getElements.and.returnValue(of(permisos));
    tokenService.isAdmin.and.returnValue(true);

    fixture.detectChanges();

    const columns = el.queryAll(By.css('th'));
    const rows = el.queryAll(By.css('tr'));

    expect(columns.length).toBe(8, "Unexpected number of columns found");

    expect(rows.length).toBe(16, "Unexpected number of rows found");

  });

});