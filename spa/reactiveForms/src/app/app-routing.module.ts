import {NgModule} from "@angular/core";
import { RouterModule, Routes} from "@angular/router";
import {SingUpFormComponent} from "./sing-up-form/sing-up-form.component";
import {HomePageComponent} from "./home-page/home-page.component";

const appRoutes: Routes = [
    {path: '', redirectTo: 'signUp-page', pathMatch: 'full'},
    {path: 'signUp-page', component: SingUpFormComponent},
    {path: 'home-page', component: HomePageComponent}
]
@NgModule({
    imports: [RouterModule.forRoot(appRoutes)],
    exports: [RouterModule]
})
export class AppRoutingModule{

}