package com.api.reactive_nutritionapi.service;

import com.api.reactive_nutritionapi.domain.constants.entity.Macronutrient;
import com.api.reactive_nutritionapi.domain.constants.entity.Pair;
import com.api.reactive_nutritionapi.domain.dtos.viewDtos.MacronutrientView;
import com.api.reactive_nutritionapi.exceptions.MacronutrientNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MacronutrientServiceImp {

  private final ConcurrentHashMap<String, Macronutrient> macronutrientMap = new ConcurrentHashMap<>();

  public Flux<MacronutrientView> getAllMacrosView() {
    return Flux.fromIterable(macronutrientMap.values())
        .map(MacronutrientView::toView);

  }

  public Mono<MacronutrientView> getMacroViewByName(String name) {
    return Mono.fromSupplier(() -> Optional.ofNullable(macronutrientMap.get(name)))
        .flatMap(optional -> optional
            .map(Mono::just)
            .orElse(Mono.error(new MacronutrientNotFoundException(name))))
        .map(MacronutrientView::toView);
  }

  public Mono<String> getAllMacrosNames() {
    return Mono.just(String.join(",", macronutrientMap.keySet()));
  }

  public Flux<Macronutrient> findAll() {
    return Flux.fromIterable(macronutrientMap.values());
  }

  @PostConstruct
  public void initData() {
    Macronutrient carbohydrates = new Macronutrient()
        .setName("Carbohydrates")
        .setDescription("The amount of carbohydrates you should consume depends on various factors, including your age, gender, activity level, health goals, and individual metabolic needs. Carbohydrate needs can vary widely from person to person.")
        .setTypes(
            List.of(
                new Pair()
                    .setKey("Simple Carbohydrates")
                    .setValue("These are made up of one or two sugar molecules. Simple carbs are found in foods like fruits (fructose), milk (lactose), and table sugar (sucrose). They are quickly digested and can lead to rapid spikes in blood sugar levels."),
                new Pair()
                    .setKey("Complex Carbohydrates")
                    .setValue("These consist of longer chains of sugar molecules. They are found in foods like whole grains (e.g., brown rice, quinoa, oats), legumes (beans, lentils), and vegetables. Complex carbs are broken down more slowly, providing sustained energy and helping to stabilize blood sugar levels.")
            ))
        .setFunctions(
            List.of(
                new Pair()
                    .setKey("Energy Source")
                    .setValue("Carbohydrates are the body's primary and most efficient source of energy. When you consume carbs, they are broken down into glucose, which is used by cells for fuel."),
                new Pair()
                    .setKey("Brain Function")
                    .setValue("The brain relies heavily on glucose for energy. Consuming adequate carbohydrates helps support cognitive function, memory, and mood."),
                new Pair()
                    .setKey("Muscle Glycogen")
                    .setValue("Carbohydrates are stored in the muscles and liver as glycogen. During exercise, muscle glycogen serves as a readily available source of energy."),
                new Pair()
                    .setKey("Preventing Protein Breakdown")
                    .setValue("Adequate carbohydrate intake spares protein from being used as an energy source. This is important for preserving muscle mass.")
            ))
        .setSources(
            List.of(
                new Pair()
                    .setKey("Grains")
                    .setValue("Whole grains like brown rice, quinoa, whole wheat, and oats provide complex carbohydrates, fiber, and various nutrients."),
                new Pair()
                    .setKey("Fruits")
                    .setValue("Fruits contain natural sugars along with vitamins, minerals, and fiber. Berries, apples, citrus fruits, and bananas are popular choices."),
                new Pair()
                    .setKey("Vegetables")
                    .setValue("Starchy vegetables (potatoes, corn) and non-starchy vegetables (leafy greens, broccoli, peppers) are rich in carbs, fiber, vitamins, and minerals."),
                new Pair()
                    .setKey("Legumes")
                    .setValue("Beans, lentils, chickpeas, and other legumes offer both carbohydrates and protein."),
                new Pair()
                    .setKey("Dairy")
                    .setValue("Dairy products contain lactose, a natural sugar. Choose low-fat or non-fat options for reduced saturated fat.")
            ))
        .setDietaryConsiderations(
            List.of(
                new Pair()
                    .setKey("Fiber")
                    .setValue("Many carbohydrate-rich foods are also high in dietary fiber, which aids digestion, supports a healthy gut, and helps control appetite."),
                new Pair()
                    .setKey("Glycemic Index (GI)")
                    .setValue("The GI measures how quickly a carbohydrate-containing food raises blood sugar levels. Foods with a low GI (complex carbs) lead to gradual increases in blood sugar, while high-GI foods (simple carbs) cause rapid spikes."),
                new Pair()
                    .setKey("Added Sugars")
                    .setValue("Be mindful of added sugars in processed foods and sugary beverages. Consuming too much added sugar can lead to health issues.")
            ))
        .setActiveState(0.50)
        .setInactiveState(0.50);

    macronutrientMap.put(carbohydrates.getName(), carbohydrates);


    Macronutrient protein = new Macronutrient()
        .setName("Protein")
        .setDescription("Protein is one of the three macronutrients essential for human health, alongside carbohydrates and fats. Proteins are made up of amino acids, which are the building blocks of the body. These amino acids play crucial roles in various bodily functions, including muscle repair, immune function, enzyme production, and hormone regulation.")
        .setTypes(List.of())
        .setFunctions(
            List.of(
                new Pair()
                    .setKey("Muscle Health")
                    .setValue("Protein is vital for building, repairing, and maintaining muscles. It's especially important for individuals engaged in physical activity, as exercise can cause muscle tissue breakdown that requires repair."),
                new Pair()
                    .setKey("Cell Structure")
                    .setValue("Proteins are the structural components of cells, tissues, and organs. They help maintain the integrity and functionality of these structures."),
                new Pair()
                    .setKey("Enzymes")
                    .setValue("Many enzymes are proteins that catalyze biochemical reactions in the body, allowing essential processes like digestion and metabolism to occur efficiently."),
                new Pair()
                    .setKey("Hormones")
                    .setValue("Some hormones, such as insulin and growth hormone, are proteins that regulate various physiological processes."),
                new Pair()
                    .setKey("Immune Function")
                    .setValue("Antibodies, which are proteins, play a critical role in the immune system by identifying and neutralizing foreign invaders like bacteria and viruses."),
                new Pair()
                    .setKey("Transport")
                    .setValue("Some proteins act as carriers, transporting nutrients, oxygen, and other molecules throughout the body.")
            ))
        .setSources(
            List.of(
                new Pair()
                    .setKey("Animal Sources")
                    .setValue("Meat, poultry, fish, eggs, dairy products, and seafood are rich in high-quality protein. These sources provide all essential amino acids in appropriate proportions."),
                new Pair()
                    .setKey("Plant Sources")
                    .setValue("Legumes (beans, lentils, peas), nuts, seeds, tofu, tempeh, and whole grains are good sources of plant-based protein. Combining different plant protein sources can help ensure a complete range of amino acids.")
            ))
        .setDietaryConsiderations(
            List.of(
                new Pair()
                    .setKey("Quality of Protein Sources")
                    .setValue("Choose a variety of protein sources to ensure a balance of essential amino acids. Animal-based sources (meat, poultry, fish, eggs, dairy) are often complete proteins, while plant-based sources (legumes, nuts, seeds, grains) may require combining different sources to ensure a complete amino acid profile."),
                new Pair()
                    .setKey("Protein Intake")
                    .setValue("Recommendations vary based on factors such as age, gender, activity level, and goals. For general health, a common guideline is about 0.8 grams of protein per kilogram of body weight per id. Athletes and those engaged in strength training may require more (1.2 to 2.0 grams per kilogram)."),
                new Pair()
                    .setKey("Protein and Weight Management")
                    .setValue("Protein-rich foods can help control appetite and contribute to feelings of fullness. Including protein in meals and snacks can support weight management goals."),
                new Pair()
                    .setKey("Individual Needs and Goals")
                    .setValue("Dietary protein needs vary widely based on factors such as age, gender, activity level, metabolism, and health status. Consider working with a registered dietitian to determine your personalized protein requirements."),
                new Pair()
                    .setKey("Limit Processed Meats")
                    .setValue("Processed meats (such as sausages, bacon, and deli meats) are often high in salt, saturated fats, and additives. Limiting their consumption supports overall health."),
                new Pair()
                    .setKey("Consider the Source")
                    .setValue("When consuming animal-based proteins, opt for lean cuts of meat, poultry without the skin, and low-fat dairy to reduce saturated fat intake"),
                new Pair()
                    .setKey("Protein Powder Supplements")
                    .setValue("Protein supplements can be convenient for individuals with increased protein needs, such as athletes or those with dietary restrictions. However, whole food sources are generally preferred due to their additional nutritional benefits.")
            ))
        .setActiveState(0.25)
        .setInactiveState(0.15);

    macronutrientMap.put(protein.getName(), protein);

    Macronutrient fat = new Macronutrient()
        .setName("Fat")
        .setDescription("")
        .setTypes(
            List.of(
                new Pair()
                    .setKey("Saturated Fats")
                    .setValue("These fats are found in animal products like meat, poultry, and dairy, as well as in some plant oils like coconut and palm oil. Consuming high amounts of saturated fats can raise cholesterol levels and increase the risk of heart disease."),
                new Pair()
                    .setKey("Monounsaturated Fats")
                    .setValue("These fats are found in olive oil, avocados, nuts, and seeds. They are considered heart-healthy and can help lower bad (LDL) cholesterol levels while maintaining or increasing good (HDL) cholesterol levels."),
                new Pair()
                    .setKey("Polyunsaturated Fats")
                    .setValue("These fats include omega-3 and omega-6 fatty acids. Omega-3s are found in fatty fish (salmon, mackerel, sardines), flaxseeds, chia seeds, and walnuts. Omega-6s are present in vegetable oils (corn, soybean, sunflower) and nuts. These fats are important for overall health and must be obtained from the diet, as the body can't produce them."),
                new Pair()
                    .setKey("Trans Fats")
                    .setValue("Trans fats are primarily found in partially hydrogenated oils, which are used in some processed foods to improve shelf life and texture. Trans fats are known to raise bad cholesterol levels and increase the risk of heart disease. Many countries have implemented regulations to limit their use in foods.")
            ))
        .setFunctions(
            List.of(
                new Pair()
                    .setKey("Energy Source")
                    .setValue("Fats are a concentrated source of energy, providing more than twice the calories per gram compared to carbohydrates and protein. They serve as a stored energy reserve in the body."),
                new Pair()
                    .setKey("Nutrient Absorption")
                    .setValue("Fats are necessary for the absorption of fat-soluble vitamins (A, D, E, K) and certain antioxidants. These vitamins are crucial for various bodily functions, including vision, bone health, and immune function."),
                new Pair()
                    .setKey("Cell Structure")
                    .setValue("Fats are essential components of cell membranes, helping maintain their integrity and allowing cells to function properly."),
                new Pair()
                    .setKey("Insulation and Protection")
                    .setValue("Fats serve as insulation to regulate body temperature and protect vital organs by acting as a cushion."),
                new Pair()
                    .setKey("Hormone Production")
                    .setValue("Fats are involved in the production of hormones, including sex hormones and hormones related to stress and inflammation.")
            ))
        .setSources(
            List.of(
                new Pair()
                    .setKey("Plant-Based Sources")
                    .setValue("Nuts,Seeds,Avocado,Oils"),
                new Pair()
                    .setKey("Animal-Based Sources")
                    .setValue("Fatty Fish,Meat and Poultry,Full-fat dairy products like cheese, yogurt, and milk contain saturated fats. Opt for reduced-fat or low-fat options to reduce saturated fat intake"),
                new Pair()
                    .setKey("Processed and Packaged Foods")
                    .setValue("Snack Foods,Baked Goods,Processed Meats"),
                new Pair()
                    .setKey("Nut Butters")
                    .setValue("Peanut Butter,Almond Butter, Cashew Butter")
            ))
        .setDietaryConsiderations(
            List.of(
                new Pair()
                    .setKey("Healthy Fat Choices")
                    .setValue("Focus on sources of healthy fats, such as nuts, seeds, avocados, olive oil, and fatty fish, while limiting sources of saturated and trans fats."),
                new Pair()
                    .setKey("Balancing Omega-3 and Omega-6")
                    .setValue("Aim for a balance between omega-3 and omega-6 fatty acids. The modern Western diet tends to be higher in omega-6s, so including more sources of omega-3s is beneficial."),
                new Pair()
                    .setKey("Portion Control")
                    .setValue("While fats are essential, they are calorie-dense. Be mindful of portion sizes to avoid excessive calorie intake."),
                new Pair()
                    .setKey("Cooking Oils")
                    .setValue("Choose cooking oils with a high smoke point for high-temperature cooking (e.g., avocado oil, coconut oil), and use oils like olive oil for low-heat cooking or as dressings."),
                new Pair()
                    .setKey("Hydrogenated and Trans Fats")
                    .setValue("Minimize or avoid foods containing partially hydrogenated oils or trans fats, as they are detrimental to heart health.")
            ))
        .setActiveState(0.20)
        .setInactiveState(0.35);

    macronutrientMap.put(fat.getName(), fat);
  }
}