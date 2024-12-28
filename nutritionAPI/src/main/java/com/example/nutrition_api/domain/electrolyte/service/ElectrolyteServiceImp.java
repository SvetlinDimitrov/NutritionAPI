package com.example.nutrition_api.domain.electrolyte.service;

import com.example.nutrition_api.domain.electrolyte.dto.ElectrolyteView;
import com.example.nutrition_api.domain.electrolyte.entity.Electrolyte;
import com.example.nutrition_api.domain.shared.entity.Pair;
import com.example.nutrition_api.infrastructure.exceptions.ElectrolyteNotFoundException;
import com.example.nutrition_api.infrastructure.mappers.ViewConverter;
import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ElectrolyteServiceImp implements ElectrolyteService {

  private final Map<String, Electrolyte> electrolyteEntityMap = new LinkedHashMap<>();
  private final ViewConverter converter;

  public ElectrolyteServiceImp(ViewConverter converter) {
    this.converter = converter;
  }

  public List<Electrolyte> findAll() {
    return electrolyteEntityMap.values().stream().toList();
  }

  public List<ElectrolyteView> getAll() {
    return electrolyteEntityMap
        .values()
        .stream()
        .map(converter::toView)
        .toList();
  }

  public ElectrolyteView getByName(String name) throws ElectrolyteNotFoundException {

    if (electrolyteEntityMap.containsKey(name)) {
      return converter.toView(electrolyteEntityMap.get(name));
    }
    throw new ElectrolyteNotFoundException(name);
  }

  public String getAllElectrolytesNames() {
    return String.join(",", electrolyteEntityMap.keySet());
  }

  @PostConstruct
  public void initData() {
    Electrolyte sodium = new Electrolyte()
        .setName("Sodium")
        .setDescription(
            "Sodium is a vital mineral and electrolyte that plays a central role in maintaining various physiological functions in the body. It's essential for regulating fluid balance, nerve transmission, muscle function, and blood pressure. While sodium is important for health, excessive intake can lead to health issues like high blood pressure and increased risk of heart disease.")
        .setFunctions(List.of(
            new Pair()
                .setKey("Fluid Balance")
                .setValue(
                    "Sodium helps regulate the balance of fluids inside and outside cells. It's a major contributor to osmotic pressure, which determines the movement of water between cells and their surroundings."),
            new Pair()
                .setKey("Nerve Transmission")
                .setValue(
                    "Sodium is essential for generating electrical signals that allow nerve cells to communicate with each other and with muscles. This is critical for muscle contraction, sensation, and other nervous system functions."),
            new Pair()
                .setKey("Muscle Contraction")
                .setValue(
                    "Sodium is involved in muscle contraction, including the contractions of skeletal muscles and the heart."),
            new Pair()
                .setKey("Blood Pressure Regulation")
                .setValue(
                    "Sodium levels influence blood volume and blood pressure. Excess sodium intake can cause the body to retain more water, leading to increased blood volume and higher blood pressure.")))
        .setSources(List.of(
            new Pair()
                .setKey("Table salt")
                .setValue("sodium chloride"),
            new Pair()
                .setKey("Processed and packaged foods")
                .setValue("canned soups, snacks, sauces"),
            new Pair()
                .setKey("-")
                .setValue("Fast food and restaurant meals"),
            new Pair()
                .setKey("Some natural foods")
                .setValue("dairy products, meat, seafood")
        ))
        .setHealthConsiderations(List.of(
            new Pair()
                .setKey("High Blood Pressure")
                .setValue(
                    "Consuming too much sodium can lead to increased blood pressure, a major risk factor for heart disease and stroke."),
            new Pair()
                .setKey("Cardiovascular Health")
                .setValue(
                    "High sodium intake is associated with an increased risk of heart disease and stroke."),
            new Pair()
                .setKey("Fluid Retention")
                .setValue(
                    "Excess sodium can lead to water retention, causing swelling and bloating.")))
        .setFemaleHigherBoundIntake(new BigDecimal("2300"))
        .setFemaleLowerBoundIntake(new BigDecimal("2300"))
        .setMaleHigherBoundIntake(new BigDecimal("2300"))
        .setMaleLowerBoundIntake(new BigDecimal("2300"))
        .setMeasure("milligrams (mg)");

    electrolyteEntityMap.put(sodium.getName(), sodium);

    Electrolyte potassium = new Electrolyte()
        .setName("Potassium")
        .setDescription(
            "Potassium is an essential mineral and electrolyte that plays a critical role in maintaining various bodily functions. It works in tandem with sodium to help regulate fluid balance, nerve transmission, muscle function, and blood pressure. Potassium is abundant in many foods, and maintaining a proper balance of potassium in the body is crucial for overall health.")
        .setFunctions(List.of(
            new Pair()
                .setKey("Fluid Balance")
                .setValue(
                    "Potassium is a key player in maintaining fluid balance within cells and their surroundings. It helps regulate osmotic pressure, which determines the movement of water in and out of cells"),
            new Pair()
                .setKey("Nerve Transmission")
                .setValue(
                    "Potassium is essential for generating electrical signals that enable nerve cells to communicate with each other and with muscles. This is vital for proper muscle function and sensation."),
            new Pair()
                .setKey("Muscle Contraction")
                .setValue(
                    "Potassium plays a central role in muscle contraction. Adequate levels of potassium are necessary for muscles, including the heart, to contract and relax properly."),
            new Pair()
                .setKey("Blood Pressure Regulation")
                .setValue(
                    "Potassium intake is closely linked to blood pressure regulation. It helps counteract the effects of sodium by promoting the excretion of sodium through urine, which can help lower blood pressure.")))
        .setSources(List.of(
            new Pair()
                .setKey("Fruits")
                .setValue("bananas, oranges, melons, avocados"),
            new Pair()
                .setKey("Vegetables")
                .setValue("spinach, potatoes, tomatoes"),
            new Pair()
                .setKey("Legumes")
                .setValue("beans, lentils"),
            new Pair()
                .setKey("Dairy products")
                .setValue("milk, yogurt"),
            new Pair()
                .setKey("-")
                .setValue("Nuts and seeds"),
            new Pair()
                .setKey("Whole grains")
                .setValue("brown rice, whole wheat"),
            new Pair()
                .setKey("Fish")
                .setValue("salmon, tuna")
        ))
        .setHealthConsiderations(List.of(
            new Pair()
                .setKey("-")
                .setValue(
                    "Maintaining a proper balance of potassium is important for overall health, but excessive intake of potassium supplements can be harmful, especially for individuals with certain medical conditions like kidney problems. Kidneys play a critical role in regulating potassium levels in the body, and compromised kidney function can lead to potassium imbalances.")))
        .setMaleLowerBoundIntake(new BigDecimal("3500"))
        .setMaleHigherBoundIntake(new BigDecimal("4700"))
        .setFemaleLowerBoundIntake(new BigDecimal("3500"))
        .setFemaleHigherBoundIntake(new BigDecimal("4700"))
        .setMeasure("milligrams (mg)");

    electrolyteEntityMap.put(potassium.getName(), potassium);

    Electrolyte calcium = new Electrolyte()
        .setName("Calcium")
        .setDescription(
            "Calcium is a vital mineral that plays a fundamental role in maintaining strong bones and teeth, supporting muscle and nerve function, and facilitating various cellular processes throughout the body. It's one of the most abundant minerals in the human body and is essential for overall health.")
        .setFunctions(List.of(
            new Pair()
                .setKey("Bone and Teeth Health")
                .setValue(
                    "Calcium is a primary building block for bones and teeth. It provides strength and rigidity to the skeletal system and helps prevent conditions like osteoporosis, which is characterized by weakened bones."),
            new Pair()
                .setKey("Muscle Contraction")
                .setValue(
                    "Calcium is crucial for muscle contraction, including both skeletal muscles and the heart. It helps regulate the contraction and relaxation of muscles."),
            new Pair()
                .setKey("Nerve Function")
                .setValue(
                    "Calcium plays a role in nerve transmission by facilitating the release of neurotransmitters, which are chemicals that transmit signals between nerve cells."),
            new Pair()
                .setKey("Blood Clotting")
                .setValue(
                    "Calcium is involved in the blood clotting process. It's necessary for the activation of certain proteins that help form blood clots to stop bleeding."),
            new Pair()
                .setKey("Cellular Signaling")
                .setValue(
                    "Calcium ions serve as important signaling molecules within cells. They are involved in numerous cellular processes, including enzyme activation and gene expression.")))
        .setSources(List.of(
            new Pair()
                .setKey("Dairy products")
                .setValue("milk, yogurt, cheese"),
            new Pair()
                .setKey("Fortified plant-based milk alternatives")
                .setValue("soy milk, almond milk"),
            new Pair()
                .setKey("Leafy green vegetables")
                .setValue("collard greens, broccoli, spinach"),
            new Pair()
                .setKey("Nuts and seeds")
                .setValue("almonds, chia seeds"),
            new Pair()
                .setKey("-")
                .setValue("Fortified cereals and breads"),
            new Pair()
                .setKey("Fish with soft, edible bones")
                .setValue("sardines, canned salmon")
        ))
        .setHealthConsiderations(List.of(
            new Pair()
                .setKey("-")
                .setValue(
                    "Calcium intake is essential, but other factors can influence its absorption and utilization in the body. For instance, vitamin D is crucial for calcium absorption in the intestines. Additionally, certain conditions like lactose intolerance or dairy allergies may require alternative sources of calcium.")))
        .setMaleLowerBoundIntake(new BigDecimal("1000"))
        .setMaleHigherBoundIntake(new BigDecimal("1000"))
        .setFemaleLowerBoundIntake(new BigDecimal("1000"))
        .setFemaleHigherBoundIntake(new BigDecimal("1000"))
        .setMeasure("milligrams (mg)");
    electrolyteEntityMap.put(calcium.getName(), calcium);

    Electrolyte magnesium = new Electrolyte()
        .setName("Magnesium")
        .setDescription("\n" +
            "Magnesium is a crucial mineral that is involved in a wide range of physiological processes within the body. It plays a vital role in supporting muscle and nerve function, maintaining a healthy immune system, regulating heart rhythm, and promoting bone health, among other functions. Magnesium is required for over 300 enzymatic reactions, making it essential for overall health and well-being.")
        .setFunctions(List.of(
            new Pair()
                .setKey("Muscle and Nerve Function")
                .setValue(
                    "Magnesium is involved in the regulation of muscle contractions and nerve impulses. It helps muscles contract and relax properly and aids in the transmission of nerve signals."),
            new Pair()
                .setKey("Energy Production")
                .setValue(
                    "Magnesium is a co-factor in many enzymatic reactions involved in energy metabolism. It's necessary for the conversion of food into energy at the cellular level."),
            new Pair()
                .setKey("Bone Health")
                .setValue(
                    "Magnesium is essential for bone health as it plays a role in bone mineralization. It works alongside other minerals like calcium and vitamin D to support strong and healthy bones."),
            new Pair()
                .setKey("Heart Health")
                .setValue(
                    "Magnesium helps regulate heart rhythm and supports the function of the cardiovascular system. It helps maintain a healthy heart rate and blood pressure."),
            new Pair()
                .setKey("Protein Synthesis")
                .setValue(
                    "Magnesium is involved in the synthesis of proteins, which are essential for various bodily functions, including immune response, tissue repair, and hormone production."),
            new Pair()
                .setKey("Nervous System Health")
                .setValue(
                    "Magnesium plays a role in maintaining the health of the nervous system. It helps reduce stress and anxiety and supports relaxation.")
        ))
        .setSources(List.of(
            new Pair()
                .setKey("Nuts and seeds")
                .setValue("almonds, cashews, pumpkin seeds"),
            new Pair()
                .setKey("Leafy green vegetables")
                .setValue("spinach, kale"),
            new Pair()
                .setKey("Whole grains")
                .setValue("brown rice, whole wheat"),
            new Pair()
                .setKey("Legumes")
                .setValue("beans, lentils"),
            new Pair()
                .setKey("Fish")
                .setValue("salmon, mackerel"),
            new Pair()
                .setKey("Dairy products")
                .setValue("yogurt, milk"),
            new Pair()
                .setKey("-")
                .setValue("Dark chocolate")
        ))
        .setHealthConsiderations(List.of(
            new Pair()
                .setKey("-")
                .setValue(
                    "Magnesium deficiency can lead to a range of health issues, including muscle cramps, fatigue, weakness, and abnormal heart rhythms. Certain medical conditions and medications can interfere with magnesium absorption or increase its excretion, leading to deficiency.")))
        .setMaleLowerBoundIntake(new BigDecimal("400"))
        .setMaleHigherBoundIntake(new BigDecimal("420"))
        .setFemaleLowerBoundIntake(new BigDecimal("310"))
        .setFemaleHigherBoundIntake(new BigDecimal("320"))
        .setMeasure("milligrams (mg)");

    electrolyteEntityMap.put(magnesium.getName(), magnesium);

    Electrolyte chloride = new Electrolyte()
        .setName("Chloride")
        .setDescription(
            "Chloride is an essential mineral that often works in tandem with other electrolytes, such as sodium and potassium, to maintain various bodily functions. It plays a crucial role in fluid balance, acid-base balance, and the function of digestive juices in the stomach.")
        .setFunctions(List.of(
            new Pair()
                .setKey("Fluid Balance")
                .setValue(
                    "Chloride, along with sodium, helps regulate fluid balance within and outside cells. This balance is crucial for maintaining proper hydration, blood pressure, and overall cell function."),
            new Pair()
                .setKey("Acid-Base Balance")
                .setValue(
                    "Chloride is a component of hydrochloric acid (HCl) in the stomach. This acid is essential for digestion and helps create an acidic environment that breaks down food and aids in the absorption of nutrients."),
            new Pair()
                .setKey("Nerve Function")
                .setValue(
                    "Chloride ions play a role in nerve impulse transmission. They help maintain the electrical gradient necessary for nerve cells to transmit signals."),
            new Pair()
                .setKey("Immune Response")
                .setValue(
                    "Chloride is involved in the immune response and the body's defense against pathogens.")
        ))
        .setSources(List.of(
            new Pair()
                .setKey("Table salt")
                .setValue("sodium chloride"),
            new Pair()
                .setKey("Processed and packaged foods")
                .setValue("canned soups, snacks, condiments"),
            new Pair()
                .setKey("-")
                .setValue("Salted meats and fish"),
            new Pair()
                .setKey("Certain vegetables")
                .setValue("celery, olives")
        ))
        .setHealthConsiderations(List.of(
            new Pair()
                .setKey("-")
                .setValue(
                    "Chloride imbalances are relatively rare and often associated with imbalances in other electrolytes, such as sodium or potassium. Excessive sodium consumption, often in the form of sodium chloride, can lead to high levels of sodium and chloride in the body, potentially contributing to issues like high blood pressure and fluid retention.")))
        .setMaleLowerBoundIntake(new BigDecimal("2300"))
        .setMaleHigherBoundIntake(new BigDecimal("2300"))
        .setFemaleLowerBoundIntake(new BigDecimal("2300"))
        .setFemaleHigherBoundIntake(new BigDecimal("2300"))
        .setMeasure("milligrams (mg)");

    electrolyteEntityMap.put(chloride.getName(), chloride);
  }
}