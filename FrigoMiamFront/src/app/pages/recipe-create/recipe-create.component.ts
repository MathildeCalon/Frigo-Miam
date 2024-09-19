import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

@Component({
  selector: 'app-recipe-create',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './recipe-create.component.html',
  styleUrls: ['./recipe-create.component.css']
})
export class RecipeCreateComponent {

  recipe = {
    title: '',
    ingredients: [],
    descript: '',
    urlImg: '',
  };

  ingredient = {
    name: '',
    quantity: ''
  }

  ingredientList = {}



  isSubmitted = false;

  newIngredient: { ingredient: string; quantity: number, value: number } = { ingredient: '', quantity: 1, value: 2 };

  constructor() { }

  addIngredient() {
    if (this.newIngredient.ingredient && this.newIngredient.quantity > 0) {
      //this.recipe.ingredients.push({ ...this.newIngredient });
      //this.newIngredient = { ingredient: '', quantity: 1, value: 2 };
    }
  }

  submitRecipe() {
    this.isSubmitted = true;
    console.log('Recette soumise', this.recipe);
  }
}

