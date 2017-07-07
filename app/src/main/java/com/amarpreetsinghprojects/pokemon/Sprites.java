package com.amarpreetsinghprojects.pokemon;

/**
 * Created by kulvi on 07/05/17.
 */

public class Sprites {
    String front_default,back_female,back_shiny_female,back_default,front_female,front_shiny_female,back_shiny,front_shiny;

    public Sprites(String front_default, String back_female, String back_shiny_female, String back_default, String front_female, String front_shiny_female, String back_shiny, String front_shiny) {
        this.front_default = front_default;
        this.back_female = back_female;
        this.back_shiny_female = back_shiny_female;
        this.back_default = back_default;
        this.front_female = front_female;
        this.front_shiny_female = front_shiny_female;
        this.back_shiny = back_shiny;
        this.front_shiny = front_shiny;
    }

    public String getFront_default() {
        return front_default;
    }

    public String getBack_female() {
        return back_female;
    }

    public String getBack_shiny_female() {
        return back_shiny_female;
    }

    public String getBack_default() {
        return back_default;
    }

    public String getFront_female() {
        return front_female;
    }

    public String getFront_shiny_female() {
        return front_shiny_female;
    }

    public String getBack_shiny() {
        return back_shiny;
    }

    public String getFront_shiny() {
        return front_shiny;
    }
}
