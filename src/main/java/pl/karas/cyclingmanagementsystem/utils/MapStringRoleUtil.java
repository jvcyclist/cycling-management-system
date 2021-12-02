package pl.karas.cyclingmanagementsystem.utils;

import pl.karas.cyclingmanagementsystem.model.ERole;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public  class MapStringRoleUtil {
    public static Map<String, ERole> mapStringERole;

    static {
        Map<String, ERole> sourceMap = new HashMap<>();
        sourceMap.put("coach_zak", ERole.ROLE_COACH_ZAK);
        sourceMap.put("coach_zakini", ERole.ROLE_COACH_ZAKINI);
        sourceMap.put("coach_mlodzik", ERole.ROLE_COACH_MLODZIK);
        sourceMap.put("coach_mlodiczka", ERole.ROLE_COACH_MLODICZKA);
        sourceMap.put("coach_junior_ml", ERole.ROLE_COACH_JUNIOR_ML);
        sourceMap.put("coach_juniorka_ml", ERole.ROLE_COACH_JUNIORKA_ML);
        sourceMap.put("coach_junior", ERole.ROLE_COACH_JUNIOR);
        sourceMap.put("coach_juniorka", ERole.ROLE_COACH_JUNIORKA);
        sourceMap.put("coach_orlik", ERole.ROLE_COACH_ORLIK);
        sourceMap.put("coach_orliczka", ERole.ROLE_COACH_ORLICZKA);
        sourceMap.put("coach_elita_m", ERole.ROLE_COACH_ELITA_M);
        sourceMap.put("coach_elita_k", ERole.ROLE_COACH_ELITA_K);
        mapStringERole = Collections.unmodifiableMap(sourceMap);
    }

    public MapStringRoleUtil(){
      
    }

}
