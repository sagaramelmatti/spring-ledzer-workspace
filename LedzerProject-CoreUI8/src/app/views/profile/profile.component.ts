import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/internal/Subscription';
import { first } from 'rxjs/internal/operators/first';

import  { AuthService  }  from '../../shared_service/auth.service';
import { UserInfo } from '../../views/auth/user-info';
import { TabsetComponent } from 'ngx-bootstrap/tabs';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit, OnDestroy {

  @ViewChild('tabSet', { static: false, }) tabSet: TabsetComponent;

  username: string;
  userInfo: UserInfo = {} as UserInfo;
  private sub: Subscription;

  constructor(
    private route: ActivatedRoute,
    private authService: AuthService
  ) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.username = params['username'];
      this.loadUserProfile(this.username);
    });
  }

  loadUserProfile(username: string) {
    this.tabSet.tabs[0].active = true;
    this.authService.getUserProfile(username)
    .pipe(first())
      .subscribe( res => {
        this.userInfo = res;
      },
        error => {
          console.log(error);
        });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

}
