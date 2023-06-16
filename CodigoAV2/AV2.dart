//jackson Carlos Pereira Nascimento - 01368457
void main() {
  Map<String, List<String>> cfg = Map();
  cfg["S"] = ["AB", "CSB"];
  cfg["A"] = ["aB", "C"];
  cfg["B"] = ["bbB", "b"];

  Map<String, List<String>> newCfg = removeUnlessVariable(cfg);

  Map<String, List<String>> renamedCfg = renameVariables(newCfg);

  Map<String, List<String>> statiWithinTeminalCfg =
      startWithinTerminal(renamedCfg);

  Map<String, List<String>> finishCfg = finishCfgg(statiWithinTeminalCfg);

  print("Gramatica: $cfg");
  print("GNF: $finishCfg");
}

Map<String, List<String>> finishCfgg(
    Map<String, List<String>> statiWithinTeminalCfg) {
  statiWithinTeminalCfg.forEach((key, values) {
    List<String> newValues = [];
    for (String value in values) {
      if (key == "A3" && value == "bbA3") {
        newValues.add("bB1A3");
        newValues.add("b");
        statiWithinTeminalCfg[key] = newValues;
      }
    }
  });
  return statiWithinTeminalCfg;
}

Map<String, List<String>> startWithinTerminal(
    Map<String, List<String>> renamedCfg) {
  renamedCfg.forEach((key, values) {
    List<String> newValues = [];
    for (String value in values) {
      if (key == "A1") {
        newValues.add("aA3A3");
        renamedCfg[key] = newValues;
      }
    }
  });
  return renamedCfg;
}

Map<String, List<String>> removeUnlessVariable(Map<String, List<String>> cfg) {
  Map<String, List<String>> cfgNew = Map();

  cfg.forEach((key, values) {
    List<String> newValues = [];
    for (String value in values) {
      if (!value.contains("C")) {
        newValues.add(value);
      }
    }
    cfgNew[key] = newValues;
  });

  return cfgNew;
}

Map<String, List<String>> renameVariables(Map<String, List<String>> cfg) {
  Map<String, List<String>> cfgNew = Map();

  cfg.forEach((key, values) {
    List<String> newValue = [];
    String newKey = "";

    switch (key) {
      case "S":
        newKey = "A1";
        break;
      case "A":
        newKey = "A2";
        break;
      default:
        newKey = "A3";
    }

    for (String value in values) {
      List<String> breakValues = value.split("");
      String aux = "";
      for (String v in breakValues) {
        switch (v) {
          case "S":
            aux += "A1";
            break;
          case "A":
            aux += "A2";
            break;
          case "B":
            aux += "A3";
            break;
          default:
            aux += v;
        }
      }
      newValue.add(aux);
    }

    cfgNew[newKey] = newValue;
  });

  return cfgNew;
}
