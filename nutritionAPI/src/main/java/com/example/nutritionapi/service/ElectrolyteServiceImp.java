package com.example.nutritionapi.service;

import com.example.nutritionapi.domain.constants.Gender;
import com.example.nutritionapi.domain.dtos.viewDtos.ElectrolyteView;
import com.example.nutritionapi.domain.entity.DailyIntakeEntity;
import com.example.nutritionapi.domain.entity.ElectrolyteEntity;
import com.example.nutritionapi.domain.entity.PairEntity;
import com.example.nutritionapi.exceptions.ElectrolyteNotFound;
import com.example.nutritionapi.repos.ElectrolyteRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElectrolyteServiceImp {
    private final ElectrolyteRepository electrolyteRepository;

    public ElectrolyteServiceImp(ElectrolyteRepository electrolyteRepository) {
        this.electrolyteRepository = electrolyteRepository;
    }

    @Cacheable("electrolytes")
    public List<ElectrolyteView> getAllViewElectrolytes() {
        return electrolyteRepository.findAll()
                .stream()
                .map(ElectrolyteView::new)
                .toList();
    }
    @Cacheable("electrolytes")
    public ElectrolyteView getElectrolyteViewByName(String name) throws ElectrolyteNotFound {
        return electrolyteRepository.findByName(name)
                .map(ElectrolyteView::new)
                .orElseThrow(() -> new ElectrolyteNotFound(name , getAllElectrolytesNames()));
    }
    @Cacheable("electrolytes")
    public String getAllElectrolytesNames() {
        return electrolyteRepository.findAll()
                .stream()
                .map(ElectrolyteEntity::getName)
                .collect(Collectors.joining(","));
    }

    @PostConstruct
    public void initData() {
        if (electrolyteRepository.count() == 0) {
            electrolyteRepository.saveAll(
                    List.of(
                            new ElectrolyteEntity()
                                    .setName("Sodium")
                                    .setDescription("Sodium is a vital mineral and electrolyte that plays a central role in maintaining various physiological functions in the body. It's essential for regulating fluid balance, nerve transmission, muscle function, and blood pressure. While sodium is important for health, excessive intake can lead to health issues like high blood pressure and increased risk of heart disease.")
                                    .setFunctions(List.of(
                                            new PairEntity()
                                                    .setKey("Fluid Balance")
                                                    .setValue("Sodium helps regulate the balance of fluids inside and outside cells. It's a major contributor to osmotic pressure, which determines the movement of water between cells and their surroundings."),
                                            new PairEntity()
                                                    .setKey("Nerve Transmission")
                                                    .setValue("Sodium is essential for generating electrical signals that allow nerve cells to communicate with each other and with muscles. This is critical for muscle contraction, sensation, and other nervous system functions."),
                                            new PairEntity()
                                                    .setKey("Muscle Contraction")
                                                    .setValue("Sodium is involved in muscle contraction, including the contractions of skeletal muscles and the heart."),
                                            new PairEntity()
                                                    .setKey("Blood Pressure Regulation")
                                                    .setValue("Sodium levels influence blood volume and blood pressure. Excess sodium intake can cause the body to retain more water, leading to increased blood volume and higher blood pressure.")))
                                    .setSources(List.of(
                                            new PairEntity()
                                                    .setKey("Table salt")
                                                    .setValue("sodium chloride"),
                                            new PairEntity()
                                                    .setKey("Processed and packaged foods")
                                                    .setValue("canned soups, snacks, sauces"),
                                            new PairEntity()
                                                    .setKey("-")
                                                    .setValue("Fast food and restaurant meals"),
                                            new PairEntity()
                                                    .setKey("Some natural foods")
                                                    .setValue("dairy products, meat, seafood")
                                    ))
                                    .setHealthConsiderations(List.of(
                                            new PairEntity()
                                                    .setKey("High Blood Pressure")
                                                    .setValue("Consuming too much sodium can lead to increased blood pressure, a major risk factor for heart disease and stroke."),
                                            new PairEntity()
                                                    .setKey("Cardiovascular Health")
                                                    .setValue("High sodium intake is associated with an increased risk of heart disease and stroke."),
                                            new PairEntity()
                                                    .setKey("Fluid Retention")
                                                    .setValue("Excess sodium can lead to water retention, causing swelling and bloating.")))
                                    .setMale(new DailyIntakeEntity()
                                            .setGender(Gender.MALE)
                                            .setLowerBound(new BigDecimal("2300"))
                                            .setUpperBound(new BigDecimal("2300"))
                                            .setMeasureUnit("milligrams (mg)"))
                                    .setFemale(new DailyIntakeEntity()
                                            .setGender(Gender.FEMALE)
                                            .setLowerBound(new BigDecimal("2300"))
                                            .setUpperBound(new BigDecimal("2300"))
                                            .setMeasureUnit("milligrams (mg)"))
                            ,new ElectrolyteEntity()
                                    .setName("Potassium")
                                    .setDescription("Potassium is an essential mineral and electrolyte that plays a critical role in maintaining various bodily functions. It works in tandem with sodium to help regulate fluid balance, nerve transmission, muscle function, and blood pressure. Potassium is abundant in many foods, and maintaining a proper balance of potassium in the body is crucial for overall health.")
                                    .setFunctions(List.of(
                                            new PairEntity()
                                                    .setKey("Fluid Balance")
                                                    .setValue("Potassium is a key player in maintaining fluid balance within cells and their surroundings. It helps regulate osmotic pressure, which determines the movement of water in and out of cells"),
                                            new PairEntity()
                                                    .setKey("Nerve Transmission")
                                                    .setValue("Potassium is essential for generating electrical signals that enable nerve cells to communicate with each other and with muscles. This is vital for proper muscle function and sensation."),
                                            new PairEntity()
                                                    .setKey("Muscle Contraction")
                                                    .setValue("Potassium plays a central role in muscle contraction. Adequate levels of potassium are necessary for muscles, including the heart, to contract and relax properly."),
                                            new PairEntity()
                                                    .setKey("Blood Pressure Regulation")
                                                    .setValue("Potassium intake is closely linked to blood pressure regulation. It helps counteract the effects of sodium by promoting the excretion of sodium through urine, which can help lower blood pressure.")))
                                    .setSources(List.of(
                                            new PairEntity()
                                                    .setKey("Fruits")
                                                    .setValue("bananas, oranges, melons, avocados"),
                                            new PairEntity()
                                                    .setKey("Vegetables")
                                                    .setValue("spinach, potatoes, tomatoes"),
                                            new PairEntity()
                                                    .setKey("Legumes")
                                                    .setValue("beans, lentils"),
                                            new PairEntity()
                                                    .setKey("Dairy products")
                                                    .setValue("milk, yogurt"),
                                            new PairEntity()
                                                    .setKey("-")
                                                    .setValue("Nuts and seeds"),
                                            new PairEntity()
                                                    .setKey("Whole grains")
                                                    .setValue("brown rice, whole wheat"),
                                            new PairEntity()
                                                    .setKey("Fish")
                                                    .setValue("salmon, tuna")

                                    ))
                                    .setHealthConsiderations(List.of(
                                            new PairEntity()
                                                    .setKey("-")
                                                    .setValue("Maintaining a proper balance of potassium is important for overall health, but excessive intake of potassium supplements can be harmful, especially for individuals with certain medical conditions like kidney problems. Kidneys play a critical role in regulating potassium levels in the body, and compromised kidney function can lead to potassium imbalances.")))
                                    .setMale(new DailyIntakeEntity()
                                            .setGender(Gender.MALE)
                                            .setLowerBound(new BigDecimal("3500"))
                                            .setUpperBound(new BigDecimal("4700"))
                                            .setMeasureUnit("milligrams (mg)"))
                                    .setFemale(new DailyIntakeEntity()
                                            .setGender(Gender.FEMALE)
                                            .setLowerBound(new BigDecimal("3500"))
                                            .setUpperBound(new BigDecimal("4700"))
                                            .setMeasureUnit("milligrams (mg)"))
                            ,new ElectrolyteEntity()
                                    .setName("Calcium")
                                    .setDescription("Calcium is a vital mineral that plays a fundamental role in maintaining strong bones and teeth, supporting muscle and nerve function, and facilitating various cellular processes throughout the body. It's one of the most abundant minerals in the human body and is essential for overall health.")
                                    .setFunctions(List.of(
                                            new PairEntity()
                                                    .setKey("Bone and Teeth Health")
                                                    .setValue("Calcium is a primary building block for bones and teeth. It provides strength and rigidity to the skeletal system and helps prevent conditions like osteoporosis, which is characterized by weakened bones."),
                                            new PairEntity()
                                                    .setKey("Muscle Contraction")
                                                    .setValue("Calcium is crucial for muscle contraction, including both skeletal muscles and the heart. It helps regulate the contraction and relaxation of muscles."),
                                            new PairEntity()
                                                    .setKey("Nerve Function")
                                                    .setValue("Calcium plays a role in nerve transmission by facilitating the release of neurotransmitters, which are chemicals that transmit signals between nerve cells."),
                                            new PairEntity()
                                                    .setKey("Blood Clotting")
                                                    .setValue("Calcium is involved in the blood clotting process. It's necessary for the activation of certain proteins that help form blood clots to stop bleeding."),
                                            new PairEntity()
                                                    .setKey("Cellular Signaling")
                                                    .setValue("Calcium ions serve as important signaling molecules within cells. They are involved in numerous cellular processes, including enzyme activation and gene expression.")))
                                    .setSources(List.of(
                                            new PairEntity()
                                                    .setKey("Dairy products")
                                                    .setValue("milk, yogurt, cheese"),
                                            new PairEntity()
                                                    .setKey("Fortified plant-based milk alternatives")
                                                    .setValue("soy milk, almond milk"),
                                            new PairEntity()
                                                    .setKey("Leafy green vegetables")
                                                    .setValue("collard greens, broccoli, spinach"),
                                            new PairEntity()
                                                    .setKey("Nuts and seeds")
                                                    .setValue("almonds, chia seeds"),
                                            new PairEntity()
                                                    .setKey("-")
                                                    .setValue("Fortified cereals and breads"),
                                            new PairEntity()
                                                    .setKey("Fish with soft, edible bones")
                                                    .setValue("sardines, canned salmon")
                                    ))
                                    .setHealthConsiderations(List.of(
                                            new PairEntity()
                                                    .setKey("-")
                                                    .setValue("Calcium intake is essential, but other factors can influence its absorption and utilization in the body. For instance, vitamin D is crucial for calcium absorption in the intestines. Additionally, certain conditions like lactose intolerance or dairy allergies may require alternative sources of calcium.")))
                                    .setMale(new DailyIntakeEntity()
                                            .setGender(Gender.MALE)
                                            .setLowerBound(new BigDecimal("1000"))
                                            .setUpperBound(new BigDecimal("1000"))
                                            .setMeasureUnit("milligrams (mg)"))
                                    .setFemale(new DailyIntakeEntity()
                                            .setGender(Gender.FEMALE)
                                            .setLowerBound(new BigDecimal("1000"))
                                            .setUpperBound(new BigDecimal("1000"))
                                            .setMeasureUnit("milligrams (mg)"))
                            ,new ElectrolyteEntity()
                                    .setName("Magnesium")
                                    .setDescription("\n" +
                                            "Magnesium is a crucial mineral that is involved in a wide range of physiological processes within the body. It plays a vital role in supporting muscle and nerve function, maintaining a healthy immune system, regulating heart rhythm, and promoting bone health, among other functions. Magnesium is required for over 300 enzymatic reactions, making it essential for overall health and well-being.")
                                    .setFunctions(List.of(
                                            new PairEntity()
                                                    .setKey("Muscle and Nerve Function")
                                                    .setValue("Magnesium is involved in the regulation of muscle contractions and nerve impulses. It helps muscles contract and relax properly and aids in the transmission of nerve signals."),
                                            new PairEntity()
                                                    .setKey("Energy Production")
                                                    .setValue("Magnesium is a co-factor in many enzymatic reactions involved in energy metabolism. It's necessary for the conversion of food into energy at the cellular level."),
                                            new PairEntity()
                                                    .setKey("Bone Health")
                                                    .setValue("Magnesium is essential for bone health as it plays a role in bone mineralization. It works alongside other minerals like calcium and vitamin D to support strong and healthy bones."),
                                            new PairEntity()
                                                    .setKey("Heart Health")
                                                    .setValue("Magnesium helps regulate heart rhythm and supports the function of the cardiovascular system. It helps maintain a healthy heart rate and blood pressure."),
                                            new PairEntity()
                                                    .setKey("Protein Synthesis")
                                                    .setValue("Magnesium is involved in the synthesis of proteins, which are essential for various bodily functions, including immune response, tissue repair, and hormone production."),
                                            new PairEntity()
                                                    .setKey("Nervous System Health")
                                                    .setValue("Magnesium plays a role in maintaining the health of the nervous system. It helps reduce stress and anxiety and supports relaxation.")
                                    ))
                                    .setSources(List.of(
                                            new PairEntity()
                                                    .setKey("Nuts and seeds")
                                                    .setValue("almonds, cashews, pumpkin seeds"),
                                            new PairEntity()
                                                    .setKey("Leafy green vegetables")
                                                    .setValue("spinach, kale"),
                                            new PairEntity()
                                                    .setKey("Whole grains")
                                                    .setValue("brown rice, whole wheat"),
                                            new PairEntity()
                                                    .setKey("Legumes")
                                                    .setValue("beans, lentils"),
                                            new PairEntity()
                                                    .setKey("Fish")
                                                    .setValue("salmon, mackerel"),
                                            new PairEntity()
                                                    .setKey("Dairy products")
                                                    .setValue("yogurt, milk"),
                                            new PairEntity()
                                                    .setKey("-")
                                                    .setValue("Dark chocolate")
                                    ))
                                    .setHealthConsiderations(List.of(
                                            new PairEntity()
                                                    .setKey("-")
                                                    .setValue("Magnesium deficiency can lead to a range of health issues, including muscle cramps, fatigue, weakness, and abnormal heart rhythms. Certain medical conditions and medications can interfere with magnesium absorption or increase its excretion, leading to deficiency.")))
                                    .setMale(new DailyIntakeEntity()
                                            .setGender(Gender.MALE)
                                            .setLowerBound(new BigDecimal("400"))
                                            .setUpperBound(new BigDecimal("420"))
                                            .setMeasureUnit("milligrams (mg)"))
                                    .setFemale(new DailyIntakeEntity()
                                            .setGender(Gender.FEMALE)
                                            .setLowerBound(new BigDecimal("310"))
                                            .setUpperBound(new BigDecimal("320"))
                                            .setMeasureUnit("milligrams (mg)"))
                            ,new ElectrolyteEntity()
                                    .setName("Chloride")
                                    .setDescription("Chloride is an essential mineral that often works in tandem with other electrolytes, such as sodium and potassium, to maintain various bodily functions. It plays a crucial role in fluid balance, acid-base balance, and the function of digestive juices in the stomach.")
                                    .setFunctions(List.of(
                                            new PairEntity()
                                                    .setKey("Fluid Balance")
                                                    .setValue("Chloride, along with sodium, helps regulate fluid balance within and outside cells. This balance is crucial for maintaining proper hydration, blood pressure, and overall cell function."),
                                            new PairEntity()
                                                    .setKey("Acid-Base Balance")
                                                    .setValue("Chloride is a component of hydrochloric acid (HCl) in the stomach. This acid is essential for digestion and helps create an acidic environment that breaks down food and aids in the absorption of nutrients."),
                                            new PairEntity()
                                                    .setKey("Nerve Function")
                                                    .setValue("Chloride ions play a role in nerve impulse transmission. They help maintain the electrical gradient necessary for nerve cells to transmit signals."),
                                            new PairEntity()
                                                    .setKey("Immune Response")
                                                    .setValue("Chloride is involved in the immune response and the body's defense against pathogens.")
                                    ))
                                    .setSources(List.of(
                                            new PairEntity()
                                                    .setKey("Table salt")
                                                    .setValue("sodium chloride"),
                                            new PairEntity()
                                                    .setKey("Processed and packaged foods")
                                                    .setValue("canned soups, snacks, condiments"),
                                            new PairEntity()
                                                    .setKey("-")
                                                    .setValue("Salted meats and fish"),
                                            new PairEntity()
                                                    .setKey("Certain vegetables")
                                                    .setValue("celery, olives")
                                    ))
                                    .setHealthConsiderations(List.of(
                                            new PairEntity()
                                                    .setKey("-")
                                                    .setValue("Chloride imbalances are relatively rare and often associated with imbalances in other electrolytes, such as sodium or potassium. Excessive sodium consumption, often in the form of sodium chloride, can lead to high levels of sodium and chloride in the body, potentially contributing to issues like high blood pressure and fluid retention.")))
                                    .setMale(new DailyIntakeEntity()
                                            .setGender(Gender.MALE)
                                            .setLowerBound(new BigDecimal("2300"))
                                            .setUpperBound(new BigDecimal("2300"))
                                            .setMeasureUnit("milligrams (mg)"))
                                    .setFemale(new DailyIntakeEntity()
                                            .setGender(Gender.FEMALE)
                                            .setLowerBound(new BigDecimal("2300"))
                                            .setUpperBound(new BigDecimal("2300"))
                                            .setMeasureUnit("milligrams (mg)"))

                    )
            );
        }
    }
}
//,new ElectrolyteEntity()
//                                    .setName("")
//                                    .setDescription("")
//                                    .setFunctions(List.of(
//                                            new PairEntity()
//                                                    .setKey("")
//                                                    .setValue(""),
//                                            new PairEntity()
//                                                    .setKey("")
//                                                    .setValue(""),
//                                            new PairEntity()
//                                                    .setKey("")
//                                                    .setValue(""),
//                                            new PairEntity()
//                                                    .setKey("")
//                                                    .setValue(""),
//                                            new PairEntity()
//                                                    .setKey("")
//                                                    .setValue("")
//                                    ))
//                                    .setSources(List.of(
//                                            new PairEntity()
//                                                    .setKey("")
//                                                    .setValue(""),
//                                            new PairEntity()
//                                                    .setKey("")
//                                                    .setValue(""),
//                                            new PairEntity()
//                                                    .setKey("")
//                                                    .setValue(""),
//                                            new PairEntity()
//                                                    .setKey("")
//                                                    .setValue("")
//                                    ))
//                                    .setHealthConsiderations(List.of(
//                                            new PairEntity()
//                                                    .setKey("")
//                                                    .setValue(""),
//                                            new PairEntity()
//                                                    .setKey("")
//                                                    .setValue(""),
//                                            new PairEntity()
//                                                    .setKey("")
//                                                    .setValue(""),
//                                            new PairEntity()
//                                                    .setKey("")
//                                                    .setValue("")))
//                                    .setMale(new DailyIntakeEntity()
//                                            .setGender(Gender.MALE)
//                                            .setLowerBound(new BigDecimal(""))
//                                            .setUpperBound(new BigDecimal(""))
//                                            .setMeasureUnit(""))
//                                    .setFemale(new DailyIntakeEntity()
//                                            .setGender(Gender.FEMALE)
//                                            .setLowerBound(new BigDecimal(""))
//                                            .setUpperBound(new BigDecimal(""))
//                                            .setMeasureUnit(""))
