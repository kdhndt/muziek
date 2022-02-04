package be.vdab.muziek.forms;

import org.hibernate.validator.constraints.Range;

//id in de form hoeft niet, je kan @PathVariable gebruiken
public record ScoreForm(@Range(min=0, max=10) int score) {
}
