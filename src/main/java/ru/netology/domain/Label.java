package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Label {
    private HashSet<String> component;
    private HashSet<String> theme;
    private HashSet<String> type;
    private HashSet<String> thirdParty;
}
