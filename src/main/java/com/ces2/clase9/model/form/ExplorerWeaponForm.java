package com.ces2.clase9.model.form;

import com.ces2.clase9.model.Explorer;
import com.ces2.clase9.model.Weapon;
import lombok.Data;

@Data
public class ExplorerWeaponForm {
    private Explorer explorer;
    private Weapon weapon;
}
