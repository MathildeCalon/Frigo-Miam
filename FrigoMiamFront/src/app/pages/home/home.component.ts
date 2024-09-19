import { Component } from '@angular/core';
import {IngredientSearchComponent} from "../../components/ingredient-search/ingredient-search.component";
import {IngredientListComponent} from "../../components/ingredient-list-home/ingredient-list.component";
import {RecipeCardShortComponent} from "../../components/recipe-card-short/recipe-card-short.component";
import {IngredientBack, IngredientFrigo, Recipe, RecipeCard, RecipeMatched} from "../../utils/types";
import {IngredientClientService} from "../../services/http/ingredient/ingredient-client.service";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    IngredientSearchComponent,
    IngredientListComponent,
    RecipeCardShortComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  constructor(private ingredientService: IngredientClientService) {
    this.getAllIngredients()
  }

  allRecipeCardsData: RecipeCard[] = [];
  allUserIngredients: string[] = [];

  addIngredient(addedIngredient: string): void {  // TODO a mettre dans util
    this.allUserIngredients.push(addedIngredient);
    console.log(`Home allUserIngredients: ${JSON.stringify(this.allUserIngredients)}`);
  }


  convertToRecipeCards(recipeList: RecipeMatched[]): RecipeCard[] {
    return recipeList.map(recipeMatched => ({
      nom: recipeMatched.recepe.nom,
      description: recipeMatched.recepe.description,
      enoughQuantity: true  // field not used in home but in frigo
    }));
  }

  getAllIngredients(): void{
    this.ingredientService.getAllIngredients().subscribe({
      next: ingredients => {
        localStorage.setItem('allIngredients', JSON.stringify(ingredients));
      },
      error: err => {
        console.error('Erreur lors de la récupération des ingrédients', err);
      }
    })
  }

}
