import {Component, Input} from '@angular/core';
import {RecipeCard} from "../../utils/types";
import {Router} from "@angular/router";

@Component({
  selector: 'app-recipe-card-short',
  standalone: true,
  imports: [],
  templateUrl: './recipe-card-short.component.html',
  styleUrl: './recipe-card-short.component.css'
})
export class RecipeCardShortComponent {

  @Input() recipeCardData ?: RecipeCard;

  constructor(private router: Router) {}

  navigateToRecipe(): void{
    if (this.recipeCardData) {
      this.router.navigate(['/recipe', this.recipeCardData.nom]);   // TODO a tester quand on aura des recettes, faudra remettre le paramètre dans app.routes.ts
    }
  }

}
