import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/app/user';
import { UserService } from '../../user-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.less']
})
export class RegisterComponent {

  userExistsError = false;
  registrationForm!: FormGroup;

  constructor(private userService: UserService, private formBuilder: FormBuilder, private router: Router) {
  }

  buildAddReservationForm() {
    return this.registrationForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.maxLength(50), Validators.pattern(/^[a-zA-Z0-9_-]{1,30}$/)]],
      email: ['', [Validators.required, Validators.maxLength(50), Validators.email]],
      password: ['', [Validators.required, Validators.maxLength(50)]],
      organization: ['', [Validators.required]]
    })
  }

  ngOnInit() {
    this.registrationForm = this.buildAddReservationForm();
  }

  submitRegistration() {
    let user: User = {
      "username": this.registrationForm.get("username")?.value,
      "email": this.registrationForm.get("email")?.value,
      "password": this.registrationForm.get("password")?.value,
      "organization": this.registrationForm.get("organization")?.value
    }

    this.userService.register(user).subscribe({
      error: () => {
        this.userExistsError = true
      },
      complete: () => this.router.navigate(['/calendar'])
    })
  }
}
