package io.github.followsclosely.rebrickable.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RebrkExternal {

    BRICKLINK("BrickLink"),
    BRICKOWL("BrickOwl"),
    LEGO("LEGO"),
    PEERON("Peeron"),
    LDRAW("LDraw");

    private final String value;

}
