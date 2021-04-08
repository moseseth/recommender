import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {Customer, Product, RecommenderService} from '../../services/recommender.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})

export class DashboardComponent {
  isSubmitted = false;
  products: string[] = [];

  constructor(private recommenderService: RecommenderService) {
  }

  submitForm(dashboardForm: NgForm): boolean {
    this.isSubmitted = true;

    if (!dashboardForm.valid) {
      return false;
    } else {
      this.recommenderService.getProduct(dashboardForm.value as Customer)
        .subscribe(data => this.products =
          data.map(value => value.type)
        );
      return true;
    }
  }
}
