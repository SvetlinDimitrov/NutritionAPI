//package com.example.nutritionapi.service;
//
//import com.example.nutritionapi.feature.electrolyte.entity.Electrolyte;
//import com.example.nutritionapi.feature.macros.entity.Macronutrient;
//import com.example.nutritionapi.feature.shared.entity.Pair;
//import com.example.nutritionapi.feature.vitamin.entity.Vitamin;
//import com.example.nutritionapi.feature.users.enums.Gender;
//import com.example.nutritionapi.feature.users.enums.WorkoutState;
//import com.example.nutritionapi.feature.nutrition_intake.entity.NutritionIntakeEntity;
//import com.example.nutritionapi.feature.record.entity.RecordEntity;
//import com.example.nutritionapi.feature.users.entity.UserEntity;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class NutrientIntakeServiceTest {
//
//    @Mock
//    private VitaminServiceImp vitaminService;
//    @Mock
//    private MacronutrientServiceImp macroService;
//    @Mock
//    private ElectrolyteServiceImp electrolyteService;
//
//    @InjectMocks
//    private NutrientIntakeService nutrientIntakeService;
//
//    private final List<Macronutrient> macronutrients = new ArrayList<>();
//    private final List<Vitamin> vitamins = new ArrayList<>();
//    private final List<Electrolyte> electrolytes = new ArrayList<>();
//    private UserEntity user;
//    private final List<NutritionIntakeEntity> nutritionIntakeEntities = new ArrayList<>();
//
//    @BeforeEach
//    void setUp() {
//
//        user = new UserEntity()
//                .setGender(Gender.MALE)
//                .setAge(24)
//                .setHeight(new BigDecimal("189"))
//                .setWorkoutState(WorkoutState.SEDENTARY)
//                .setKilograms(new BigDecimal("84.1"));
//
//        addMacros();
//        addVitamins();
//        addElectrolytes();
//
//        RecordEntity record = new RecordEntity();
//        record.setDailyCalories(new BigDecimal("3000"));
//        nutritionIntakeEntities
//                .addAll(
//                        List.of(new NutritionIntakeEntity()
//                                        .setNutrientName(vitamins.get(0).getName())
//                                        .setNutrientType("Vitamin")
//                                        .setMeasurement(vitamins.get(0).getMeasure())
//                                        .setLowerBoundIntake(vitamins.get(0).getMaleLowerBoundIntake())
//                                        .setUpperBoundIntake(vitamins.get(0).getMaleHigherBoundIntake())
//                                        .setRecord(record),
//                                new NutritionIntakeEntity()
//                                        .setNutrientName(vitamins.get(1).getName())
//                                        .setNutrientType("Vitamin")
//                                        .setMeasurement(vitamins.get(1).getMeasure())
//                                        .setLowerBoundIntake(vitamins.get(1).getMaleLowerBoundIntake())
//                                        .setUpperBoundIntake(vitamins.get(1).getMaleHigherBoundIntake())
//                                        .setRecord(record),
//                                new NutritionIntakeEntity()
//                                        .setNutrientName(electrolytes.get(0).getName())
//                                        .setNutrientType("Electrolyte")
//                                        .setMeasurement(electrolytes.get(0).getMeasure())
//                                        .setLowerBoundIntake(electrolytes.get(0).getMaleLowerBoundIntake())
//                                        .setUpperBoundIntake(electrolytes.get(0).getMaleHigherBoundIntake())
//                                        .setRecord(record),
//                                new NutritionIntakeEntity()
//                                        .setNutrientName(electrolytes.get(1).getName())
//                                        .setNutrientType("Electrolyte")
//                                        .setMeasurement(electrolytes.get(1).getMeasure())
//                                        .setLowerBoundIntake(electrolytes.get(1).getMaleLowerBoundIntake())
//                                        .setUpperBoundIntake(electrolytes.get(1).getMaleHigherBoundIntake())
//                                        .setRecord(record),
//                                new NutritionIntakeEntity()
//                                        .setNutrientName(macronutrients.get(0).getName())
//                                        .setNutrientType("Macronutrient")
//                                        .setMeasurement("grams (g)")
//                                        .setLowerBoundIntake(
//                                                record
//                                                .getDailyCalories()
//                                                .multiply(BigDecimal.valueOf(macronutrients.get(0).getInactiveState()))
//                                                .divide(new BigDecimal("4"), RoundingMode.HALF_UP))
//                                        .setUpperBoundIntake(
//                                                record
//                                                .getDailyCalories()
//                                                .multiply(BigDecimal.valueOf(macronutrients.get(0).getInactiveState()))
//                                                .divide(new BigDecimal("4"), RoundingMode.HALF_UP))
//                                        .setRecord(record),
//                                new NutritionIntakeEntity()
//                                        .setNutrientName(macronutrients.get(1).getName())
//                                        .setNutrientType("Macronutrient")
//                                        .setMeasurement("grams (g)")
//                                        .setLowerBoundIntake(
//                                                record
//                                                        .getDailyCalories()
//                                                        .multiply(BigDecimal.valueOf(macronutrients.get(1).getInactiveState()))
//                                                        .divide(new BigDecimal("4"), RoundingMode.HALF_UP))
//                                        .setUpperBoundIntake(
//                                                record
//                                                        .getDailyCalories()
//                                                        .multiply(BigDecimal.valueOf(macronutrients.get(1).getInactiveState()))
//                                                        .divide(new BigDecimal("4"), RoundingMode.HALF_UP))
//                                        .setRecord(record)
//                                )
//                );
//        user.setRecords(List.of(record));
//    }
//
//    private void addElectrolytes() {
//        electrolytes.addAll(
//                List.of(
//                        new Electrolyte()
//                                .setName("Sodium")
//                                .setDescription("Sodium is a vital mineral and electrolyte that plays a central role in maintaining various physiological functions in the body. It's essential for regulating fluid balance, nerve transmission, muscle function, and blood pressure. While sodium is important for health, excessive intake can lead to health issues like high blood pressure and increased risk of heart disease.")
//                                .setFunctions(List.of(
//                                        new Pair()
//                                                .setKey("Fluid Balance")
//                                                .setValue("Sodium helps regulate the balance of fluids inside and outside cells. It's a major contributor to osmotic pressure, which determines the movement of water between cells and their surroundings."),
//                                        new Pair()
//                                                .setKey("Nerve Transmission")
//                                                .setValue("Sodium is essential for generating electrical signals that allow nerve cells to communicate with each other and with muscles. This is critical for muscle contraction, sensation, and other nervous system functions."),
//                                        new Pair()
//                                                .setKey("Muscle Contraction")
//                                                .setValue("Sodium is involved in muscle contraction, including the contractions of skeletal muscles and the heart."),
//                                        new Pair()
//                                                .setKey("Blood Pressure Regulation")
//                                                .setValue("Sodium levels influence blood volume and blood pressure. Excess sodium intake can cause the body to retain more water, leading to increased blood volume and higher blood pressure.")))
//                                .setSources(List.of(
//                                        new Pair()
//                                                .setKey("Table salt")
//                                                .setValue("sodium chloride"),
//                                        new Pair()
//                                                .setKey("Processed and packaged foods")
//                                                .setValue("canned soups, snacks, sauces"),
//                                        new Pair()
//                                                .setKey("-")
//                                                .setValue("Fast food and restaurant meals"),
//                                        new Pair()
//                                                .setKey("Some natural foods")
//                                                .setValue("dairy products, meat, seafood")
//                                ))
//                                .setHealthConsiderations(List.of(
//                                        new Pair()
//                                                .setKey("High Blood Pressure")
//                                                .setValue("Consuming too much sodium can lead to increased blood pressure, a major risk factor for heart disease and stroke."),
//                                        new Pair()
//                                                .setKey("Cardiovascular Health")
//                                                .setValue("High sodium intake is associated with an increased risk of heart disease and stroke."),
//                                        new Pair()
//                                                .setKey("Fluid Retention")
//                                                .setValue("Excess sodium can lead to water retention, causing swelling and bloating.")))
//                                .setFemaleHigherBoundIntake(new BigDecimal("2300"))
//                                .setFemaleLowerBoundIntake(new BigDecimal("2300"))
//                                .setMaleHigherBoundIntake(new BigDecimal("2300"))
//                                .setMaleLowerBoundIntake(new BigDecimal("2300"))
//                                .setMeasure("milligrams (mg)"),
//
//                        new Electrolyte()
//                                .setName("Potassium")
//                                .setDescription("Potassium is an essential mineral and electrolyte that plays a critical role in maintaining various bodily functions. It works in tandem with sodium to help regulate fluid balance, nerve transmission, muscle function, and blood pressure. Potassium is abundant in many foods, and maintaining a proper balance of potassium in the body is crucial for overall health.")
//                                .setFunctions(List.of(
//                                        new Pair()
//                                                .setKey("Fluid Balance")
//                                                .setValue("Potassium is a key player in maintaining fluid balance within cells and their surroundings. It helps regulate osmotic pressure, which determines the movement of water in and out of cells"),
//                                        new Pair()
//                                                .setKey("Nerve Transmission")
//                                                .setValue("Potassium is essential for generating electrical signals that enable nerve cells to communicate with each other and with muscles. This is vital for proper muscle function and sensation."),
//                                        new Pair()
//                                                .setKey("Muscle Contraction")
//                                                .setValue("Potassium plays a central role in muscle contraction. Adequate levels of potassium are necessary for muscles, including the heart, to contract and relax properly."),
//                                        new Pair()
//                                                .setKey("Blood Pressure Regulation")
//                                                .setValue("Potassium intake is closely linked to blood pressure regulation. It helps counteract the effects of sodium by promoting the excretion of sodium through urine, which can help lower blood pressure.")))
//                                .setSources(List.of(
//                                        new Pair()
//                                                .setKey("Fruits")
//                                                .setValue("bananas, oranges, melons, avocados"),
//                                        new Pair()
//                                                .setKey("Vegetables")
//                                                .setValue("spinach, potatoes, tomatoes"),
//                                        new Pair()
//                                                .setKey("Legumes")
//                                                .setValue("beans, lentils"),
//                                        new Pair()
//                                                .setKey("Dairy products")
//                                                .setValue("milk, yogurt"),
//                                        new Pair()
//                                                .setKey("-")
//                                                .setValue("Nuts and seeds"),
//                                        new Pair()
//                                                .setKey("Whole grains")
//                                                .setValue("brown rice, whole wheat"),
//                                        new Pair()
//                                                .setKey("Fish")
//                                                .setValue("salmon, tuna")
//                                ))
//                                .setHealthConsiderations(List.of(
//                                        new Pair()
//                                                .setKey("-")
//                                                .setValue("Maintaining a proper balance of potassium is important for overall health, but excessive intake of potassium supplements can be harmful, especially for individuals with certain medical conditions like kidney problems. Kidneys play a critical role in regulating potassium levels in the body, and compromised kidney function can lead to potassium imbalances.")))
//                                .setMaleLowerBoundIntake(new BigDecimal("3500"))
//                                .setMaleHigherBoundIntake(new BigDecimal("4700"))
//                                .setFemaleLowerBoundIntake(new BigDecimal("3500"))
//                                .setFemaleHigherBoundIntake(new BigDecimal("4700"))
//                                .setMeasure("milligrams (mg)")
//                )
//        );
//    }
//
//    private void addVitamins() {
//        vitamins.addAll(
//                List.of(
//                        new Vitamin()
//                                .setName("A")
//                                .setDescription("Vitamin A is a fat-soluble vitamin that plays a critical role in various bodily functions, including vision, immune system support, and maintaining healthy skin. It exists in several forms, including retinol, retinal, and retinoic acid, which have distinct functions in the body.")
//                                .setFunctions(List.of(
//                                        new Pair()
//                                                .setKey("Vision")
//                                                .setValue(" Vitamin A is essential for maintaining good vision. It is a component of rhodopsin, a protein in the retina that allows the eyes to adjust to changes in light and facilitates vision in low-light conditions. A deficiency in vitamin A can lead to night blindness and other visual impairments."),
//                                        new Pair()
//                                                .setKey("Immune System Support")
//                                                .setValue("Vitamin A helps maintain the integrity of the mucosal surfaces, such as the linings of the respiratory, digestive, and urinary tracts. This is important for preventing infections and supporting immune responses."),
//                                        new Pair()
//                                                .setKey("Skin Health")
//                                                .setValue("Vitamin A plays a role in skin health by promoting the production of skin cells and supporting the health of mucous membranes. It's often used in skincare products for its potential to improve skin texture and reduce signs of aging."),
//                                        new Pair()
//                                                .setKey("Cell Differentiation")
//                                                .setValue("Vitamin A is involved in the process of cell differentiation, which is crucial for normal growth and development, especially during embryonic development.")))
//                                .setSources(List.of(
//                                        new Pair()
//                                                .setKey("Preformed Vitamin A (Retinoids)")
//                                                .setValue("Found in animal products such as liver, fish, eggs, and dairy. These foods contain retinol, which is a form of preformed vitamin A."),
//                                        new Pair()
//                                                .setKey("Provitamin A Carotenoids")
//                                                .setValue(" Found in plant-based foods, primarily in the form of beta-carotene. Beta-carotene is a pigment that gives fruits and vegetables their orange, red, and yellow colors. The body can convert provitamin A carotenoids into active vitamin A as needed. Foods rich in beta-carotene include carrots, sweet potatoes, spinach, kale, and butternut squash.")
//                                ))
//                                .setMaleLowerBoundIntake(new BigDecimal("700"))
//                                .setMaleHigherBoundIntake(new BigDecimal("900"))
//                                .setFemaleLowerBoundIntake(new BigDecimal("600"))
//                                .setFemaleHigherBoundIntake(new BigDecimal("700"))
//                                .setMeasure("micrograms of retinol activity equivalents (RAE)"),
//
//
//                        new Vitamin()
//                                .setName("D")
//                                .setDescription("Vitamin D is a fat-soluble vitamin that is essential for several important functions in the body. Often referred to as the \"sunshine vitamin,\" it can be synthesized by the skin when exposed to sunlight. Vitamin D also comes from dietary sources and supplements. Here's more information about vitamin D")
//                                .setFunctions(List.of(
//                                        new Pair()
//                                                .setKey("Bone Health")
//                                                .setValue("One of the primary functions of vitamin D is to regulate calcium and phosphorus absorption in the intestines. This is crucial for maintaining strong and healthy bones. Vitamin D helps ensure that calcium is properly deposited in bones, which is important for preventing conditions like osteoporosis and fractures."),
//                                        new Pair()
//                                                .setKey("Immune System Support")
//                                                .setValue("Vitamin D plays a role in modulating the immune system. It helps regulate immune responses and supports the body's defense mechanisms against infections."),
//                                        new Pair()
//                                                .setKey("Cell Growth and Differentiation")
//                                                .setValue("Vitamin D is involved in cell growth, replication, and differentiation. It is important for the normal development of cells and tissues."),
//                                        new Pair()
//                                                .setKey("Hormone Regulation")
//                                                .setValue("Vitamin D acts as a hormone in the body, influencing various physiological processes. It has been linked to the regulation of insulin secretion, blood pressure, and the functioning of the cardiovascular system.")))
//                                .setSources(List.of(
//                                        new Pair()
//                                                .setKey("Sunlight")
//                                                .setValue("The skin can produce vitamin D when exposed to ultraviolet B (UVB) sunlight. However, factors like skin color, geographical location, time of day, and sunscreen use can affect the body's ability to synthesize vitamin D from sunlight."),
//                                        new Pair()
//                                                .setKey("Dietary Sources")
//                                                .setValue("While there are fewer natural dietary sources of vitamin D, some foods are fortified with it. Dietary sources include fatty fish (salmon, mackerel, sardines), cod liver oil, egg yolks, and fortified foods like milk, orange juice, and cereal.")
//                                ))
//                                .setMaleLowerBoundIntake(new BigDecimal("600"))
//                                .setMaleHigherBoundIntake(new BigDecimal("800"))
//                                .setFemaleLowerBoundIntake(new BigDecimal("600"))
//                                .setFemaleHigherBoundIntake(new BigDecimal("800"))
//                                .setMeasure("international units (IU)")
//                )
//        );
//    }
//
//    private void addMacros() {
//        macronutrients.addAll(
//                List.of(
//                        new Macronutrient()
//                                .setName("Carbohydrates")
//                                .setDescription("The amount of carbohydrates you should consume depends on various factors, including your age, gender, activity level, health goals, and individual metabolic needs. Carbohydrate needs can vary widely from person to person.")
//                                .setTypes(
//                                        List.of(
//                                                new Pair()
//                                                        .setKey("Simple Carbohydrates")
//                                                        .setValue("These are made up of one or two sugar molecules. Simple carbs are found in foods like fruits (fructose), milk (lactose), and table sugar (sucrose). They are quickly digested and can lead to rapid spikes in blood sugar levels."),
//                                                new Pair()
//                                                        .setKey("Complex Carbohydrates")
//                                                        .setValue("These consist of longer chains of sugar molecules. They are found in foods like whole grains (e.g., brown rice, quinoa, oats), legumes (beans, lentils), and vegetables. Complex carbs are broken down more slowly, providing sustained energy and helping to stabilize blood sugar levels.")
//                                        ))
//                                .setFunctions(
//                                        List.of(
//                                                new Pair()
//                                                        .setKey("Energy Source")
//                                                        .setValue("Carbohydrates are the body's primary and most efficient source of energy. When you consume carbs, they are broken down into glucose, which is used by cells for fuel."),
//                                                new Pair()
//                                                        .setKey("Brain Function")
//                                                        .setValue("The brain relies heavily on glucose for energy. Consuming adequate carbohydrates helps support cognitive function, memory, and mood."),
//                                                new Pair()
//                                                        .setKey("Muscle Glycogen")
//                                                        .setValue("Carbohydrates are stored in the muscles and liver as glycogen. During exercise, muscle glycogen serves as a readily available source of energy."),
//                                                new Pair()
//                                                        .setKey("Preventing Protein Breakdown")
//                                                        .setValue("Adequate carbohydrate intake spares protein from being used as an energy source. This is important for preserving muscle mass.")
//                                        ))
//                                .setSources(
//                                        List.of(
//                                                new Pair()
//                                                        .setKey("Grains")
//                                                        .setValue("Whole grains like brown rice, quinoa, whole wheat, and oats provide complex carbohydrates, fiber, and various nutrients."),
//                                                new Pair()
//                                                        .setKey("Fruits")
//                                                        .setValue("Fruits contain natural sugars along with vitamins, minerals, and fiber. Berries, apples, citrus fruits, and bananas are popular choices."),
//                                                new Pair()
//                                                        .setKey("Vegetables")
//                                                        .setValue("Starchy vegetables (potatoes, corn) and non-starchy vegetables (leafy greens, broccoli, peppers) are rich in carbs, fiber, vitamins, and minerals."),
//                                                new Pair()
//                                                        .setKey("Legumes")
//                                                        .setValue("Beans, lentils, chickpeas, and other legumes offer both carbohydrates and protein."),
//                                                new Pair()
//                                                        .setKey("Dairy")
//                                                        .setValue("Dairy products contain lactose, a natural sugar. Choose low-fat or non-fat options for reduced saturated fat.")
//                                        ))
//                                .setDietaryConsiderations(
//                                        List.of(
//                                                new Pair()
//                                                        .setKey("Fiber")
//                                                        .setValue("Many carbohydrate-rich foods are also high in dietary fiber, which aids digestion, supports a healthy gut, and helps control appetite."),
//                                                new Pair()
//                                                        .setKey("Glycemic Index (GI)")
//                                                        .setValue("The GI measures how quickly a carbohydrate-containing food raises blood sugar levels. Foods with a low GI (complex carbs) lead to gradual increases in blood sugar, while high-GI foods (simple carbs) cause rapid spikes."),
//                                                new Pair()
//                                                        .setKey("Added Sugars")
//                                                        .setValue("Be mindful of added sugars in processed foods and sugary beverages. Consuming too much added sugar can lead to health issues.")
//                                        ))
//                                .setActiveState(0.50)
//                                .setInactiveState(0.50),
//                        new Macronutrient()
//                                .setName("Protein")
//                                .setDescription("Protein is one of the three macronutrients essential for human health, alongside carbohydrates and fats. Proteins are made up of amino acids, which are the building blocks of the body. These amino acids play crucial roles in various bodily functions, including muscle repair, immune function, enzyme production, and hormone regulation.")
//                                .setTypes(List.of())
//                                .setFunctions(
//                                        List.of(
//                                                new Pair()
//                                                        .setKey("Muscle Health")
//                                                        .setValue("Protein is vital for building, repairing, and maintaining muscles. It's especially important for individuals engaged in physical activity, as exercise can cause muscle tissue breakdown that requires repair."),
//                                                new Pair()
//                                                        .setKey("Cell Structure")
//                                                        .setValue("Proteins are the structural components of cells, tissues, and organs. They help maintain the integrity and functionality of these structures."),
//                                                new Pair()
//                                                        .setKey("Enzymes")
//                                                        .setValue("Many enzymes are proteins that catalyze biochemical reactions in the body, allowing essential processes like digestion and metabolism to occur efficiently."),
//                                                new Pair()
//                                                        .setKey("Hormones")
//                                                        .setValue("Some hormones, such as insulin and growth hormone, are proteins that regulate various physiological processes."),
//                                                new Pair()
//                                                        .setKey("Immune Function")
//                                                        .setValue("Antibodies, which are proteins, play a critical role in the immune system by identifying and neutralizing foreign invaders like bacteria and viruses."),
//                                                new Pair()
//                                                        .setKey("Transport")
//                                                        .setValue("Some proteins act as carriers, transporting nutrients, oxygen, and other molecules throughout the body.")
//                                        ))
//                                .setSources(
//                                        List.of(
//                                                new Pair()
//                                                        .setKey("Animal Sources")
//                                                        .setValue("Meat, poultry, fish, eggs, dairy products, and seafood are rich in high-quality protein. These sources provide all essential amino acids in appropriate proportions."),
//                                                new Pair()
//                                                        .setKey("Plant Sources")
//                                                        .setValue("Legumes (beans, lentils, peas), nuts, seeds, tofu, tempeh, and whole grains are good sources of plant-based protein. Combining different plant protein sources can help ensure a complete range of amino acids.")
//                                        ))
//                                .setDietaryConsiderations(
//                                        List.of(
//                                                new Pair()
//                                                        .setKey("Quality of Protein Sources")
//                                                        .setValue("Choose a variety of protein sources to ensure a balance of essential amino acids. Animal-based sources (meat, poultry, fish, eggs, dairy) are often complete proteins, while plant-based sources (legumes, nuts, seeds, grains) may require combining different sources to ensure a complete amino acid profile."),
//                                                new Pair()
//                                                        .setKey("Protein Intake")
//                                                        .setValue("Recommendations vary based on factors such as age, gender, activity level, and goals. For general health, a common guideline is about 0.8 grams of protein per kilogram of body weight per day. Athletes and those engaged in strength training may require more (1.2 to 2.0 grams per kilogram)."),
//                                                new Pair()
//                                                        .setKey("Protein and Weight Management")
//                                                        .setValue("Protein-rich foods can help control appetite and contribute to feelings of fullness. Including protein in meals and snacks can support weight management goals."),
//                                                new Pair()
//                                                        .setKey("Individual Needs and Goals")
//                                                        .setValue("Dietary protein needs vary widely based on factors such as age, gender, activity level, metabolism, and health status. Consider working with a registered dietitian to determine your personalized protein requirements."),
//                                                new Pair()
//                                                        .setKey("Limit Processed Meats")
//                                                        .setValue("Processed meats (such as sausages, bacon, and deli meats) are often high in salt, saturated fats, and additives. Limiting their consumption supports overall health."),
//                                                new Pair()
//                                                        .setKey("Consider the Source")
//                                                        .setValue("When consuming animal-based proteins, opt for lean cuts of meat, poultry without the skin, and low-fat dairy to reduce saturated fat intake"),
//                                                new Pair()
//                                                        .setKey("Protein Powder Supplements")
//                                                        .setValue("Protein supplements can be convenient for individuals with increased protein needs, such as athletes or those with dietary restrictions. However, whole food sources are generally preferred due to their additional nutritional benefits.")
//                                        ))
//                                .setActiveState(0.25)
//                                .setInactiveState(0.15)
//                )
//        );
//    }
//
//    @Test
//    void create_calculateAndCreateNutritionIntake_successful() {
//        when(macroService.findAll()).thenReturn(macronutrients);
//        when(vitaminService.findAll()).thenReturn(vitamins);
//        when(electrolyteService.findAll()).thenReturn(electrolytes);
//
//        List<NutritionIntakeEntity> result = nutrientIntakeService.create(
//                user.getGender(),
//                user.getRecords().get(0).getDailyCalories(),
//                user.getWorkoutState(),
//                user.getRecords().get(0)
//        );
//
//        assertEquals(nutritionIntakeEntities , result);
//    }
//}