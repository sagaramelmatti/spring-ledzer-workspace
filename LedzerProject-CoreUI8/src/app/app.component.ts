import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import  { AuthService  }  from './shared_service/auth.service';
import { UserInfo } from './views/auth/user-info';


@Component({
  selector: 'body',
  template: '<router-outlet></router-outlet>'
})
export class AppComponent implements OnInit {

  private currentUser: UserInfo;
  isCollapsed = true;

  constructor(private router: Router,
    private toastr: ToastrService,
    private authService: AuthService) { }

  ngOnInit() {
    this.router.events.subscribe((evt) => {
      if (!(evt instanceof NavigationEnd)) {
        return;
      }
      window.scrollTo(0, 0);
    });

    this.authService.currentUser$.subscribe((user: UserInfo) => {
      this.currentUser = user;
    })

  }

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  logout() {
    this.authService.logout();
    this.toastr.success("You're successfully logged out.", "Polling App");
    this.router.navigate(['/auth/login']);
  }

  get userInfo() {
    const currentUser = this.authService.getCurrentUser();
    if (!currentUser) {
      return {} as UserInfo;
    }
    return currentUser;
  }

}
