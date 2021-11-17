package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Label {
    private Set<String> component = new HashSet<>();
    private Set<String> theme = new HashSet<>();
    private Set<String> thirdParty = new HashSet<>();
    private Set<String> type = new HashSet<>();

    public Set<String> labelsSet() {
        Set<String> labels = new LinkedHashSet<>();
        labels.addAll(component);
        labels.addAll(theme);
        labels.addAll(thirdParty);
        labels.addAll(type);

        return labels;
    }
}
