package com.example.nutritionapi.service;

import com.example.nutritionapi.domain.constants.Gender;
import com.example.nutritionapi.domain.dtos.VitaminView;
import com.example.nutritionapi.domain.entity.DailyIntake;
import com.example.nutritionapi.domain.entity.PairEntity;
import com.example.nutritionapi.domain.entity.VitaminEntity;
import com.example.nutritionapi.exceptions.VitaminNotFound;
import com.example.nutritionapi.repos.VitaminRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VitaminServiceImp {
    private final VitaminRepository vitaminRepository;

    public VitaminServiceImp(VitaminRepository vitaminRepository) {
        this.vitaminRepository = vitaminRepository;
    }

    @PostConstruct
    public void initVitamins() {
        if (vitaminRepository.count() == 0) {
            vitaminRepository.saveAll(List.of(
                    new VitaminEntity()
                            .setName("A")
                            .setDescription("Vitamin A is a fat-soluble vitamin that plays a critical role in various bodily functions, including vision, immune system support, and maintaining healthy skin. It exists in several forms, including retinol, retinal, and retinoic acid, which have distinct functions in the body.")
                            .setFunctions(List.of(
                                    new PairEntity()
                                            .setKey("Vision")
                                            .setValue(" Vitamin A is essential for maintaining good vision. It is a component of rhodopsin, a protein in the retina that allows the eyes to adjust to changes in light and facilitates vision in low-light conditions. A deficiency in vitamin A can lead to night blindness and other visual impairments."),
                                    new PairEntity()
                                            .setKey("Immune System Support")
                                            .setValue("Vitamin A helps maintain the integrity of the mucosal surfaces, such as the linings of the respiratory, digestive, and urinary tracts. This is important for preventing infections and supporting immune responses."),
                                    new PairEntity()
                                            .setKey("Skin Health")
                                            .setValue("Vitamin A plays a role in skin health by promoting the production of skin cells and supporting the health of mucous membranes. It's often used in skincare products for its potential to improve skin texture and reduce signs of aging."),
                                    new PairEntity()
                                            .setKey("Cell Differentiation")
                                            .setValue("Vitamin A is involved in the process of cell differentiation, which is crucial for normal growth and development, especially during embryonic development.")))
                            .setSources(List.of(
                                    new PairEntity()
                                            .setKey("Preformed Vitamin A (Retinoids)")
                                            .setValue("Found in animal products such as liver, fish, eggs, and dairy. These foods contain retinol, which is a form of preformed vitamin A."),
                                    new PairEntity()
                                            .setKey("Provitamin A Carotenoids")
                                            .setValue(" Found in plant-based foods, primarily in the form of beta-carotene. Beta-carotene is a pigment that gives fruits and vegetables their orange, red, and yellow colors. The body can convert provitamin A carotenoids into active vitamin A as needed. Foods rich in beta-carotene include carrots, sweet potatoes, spinach, kale, and butternut squash.")
                            ))
                            .setMale(new DailyIntake()
                                    .setGender(Gender.MALE)
                                    .setLowerBound(new BigDecimal("700"))
                                    .setUpperBound(new BigDecimal("900"))
                                    .setMeasureUnit("micrograms of retinol activity equivalents (RAE)"))
                            .setFemale(new DailyIntake()
                                    .setGender(Gender.FEMALE)
                                    .setLowerBound(new BigDecimal("600"))
                                    .setUpperBound(new BigDecimal("700"))
                                    .setMeasureUnit("micrograms of retinol activity equivalents (RAE)")
                            ),
                    new VitaminEntity()
                            .setName("D")
                            .setDescription("Vitamin D is a fat-soluble vitamin that is essential for several important functions in the body. Often referred to as the \"sunshine vitamin,\" it can be synthesized by the skin when exposed to sunlight. Vitamin D also comes from dietary sources and supplements. Here's more information about vitamin D")
                            .setFunctions(List.of(
                                    new PairEntity()
                                            .setKey("Bone Health")
                                            .setValue("One of the primary functions of vitamin D is to regulate calcium and phosphorus absorption in the intestines. This is crucial for maintaining strong and healthy bones. Vitamin D helps ensure that calcium is properly deposited in bones, which is important for preventing conditions like osteoporosis and fractures."),
                                    new PairEntity()
                                            .setKey("Immune System Support")
                                            .setValue("Vitamin D plays a role in modulating the immune system. It helps regulate immune responses and supports the body's defense mechanisms against infections."),
                                    new PairEntity()
                                            .setKey("Cell Growth and Differentiation")
                                            .setValue("Vitamin D is involved in cell growth, replication, and differentiation. It is important for the normal development of cells and tissues."),
                                    new PairEntity()
                                            .setKey("Hormone Regulation")
                                            .setValue("Vitamin D acts as a hormone in the body, influencing various physiological processes. It has been linked to the regulation of insulin secretion, blood pressure, and the functioning of the cardiovascular system.")))
                            .setSources(List.of(
                                    new PairEntity()
                                            .setKey("Sunlight")
                                            .setValue("The skin can produce vitamin D when exposed to ultraviolet B (UVB) sunlight. However, factors like skin color, geographical location, time of day, and sunscreen use can affect the body's ability to synthesize vitamin D from sunlight."),
                                    new PairEntity()
                                            .setKey("Dietary Sources")
                                            .setValue("While there are fewer natural dietary sources of vitamin D, some foods are fortified with it. Dietary sources include fatty fish (salmon, mackerel, sardines), cod liver oil, egg yolks, and fortified foods like milk, orange juice, and cereal.")
                            ))
                            .setMale(new DailyIntake()
                                    .setGender(Gender.MALE)
                                    .setLowerBound(new BigDecimal("600"))
                                    .setUpperBound(new BigDecimal("800"))
                                    .setMeasureUnit("international units (IU)"))
                            .setFemale(new DailyIntake()
                                    .setGender(Gender.FEMALE)
                                    .setLowerBound(new BigDecimal("600"))
                                    .setUpperBound(new BigDecimal("800"))
                                    .setMeasureUnit("international units (IU)"))
                    ,new VitaminEntity()
                            .setName("E")
                            .setDescription("Vitamin E is a fat-soluble vitamin with antioxidant properties, meaning it helps protect cells from damage caused by harmful molecules called free radicals. It exists in eight different forms, with alpha-tocopherol being the most common and biologically active form in the human body.")
                            .setFunctions(List.of(
                                    new PairEntity()
                                            .setKey("Antioxidant Protection")
                                            .setValue("Vitamin E's primary role is as an antioxidant. It helps neutralize free radicals, which are unstable molecules that can damage cells, DNA, and tissues. By reducing oxidative stress, vitamin E contributes to overall cellular health and may help prevent chronic diseases."),
                                    new PairEntity()
                                            .setKey("Immune Support")
                                            .setValue("Vitamin E plays a role in supporting the immune system's response to infections and diseases. It helps regulate immune cell functions and enhances the body's defense mechanisms."),
                                    new PairEntity()
                                            .setKey("Skin Health")
                                            .setValue("Vitamin E is often associated with skin health. It can help moisturize and protect the skin from damage caused by UV radiation and environmental pollutants."),
                                    new PairEntity()
                                            .setKey("Cardiovascular Health")
                                            .setValue("Some research suggests that vitamin E might contribute to heart health by preventing the oxidation of low-density lipoprotein (LDL) cholesterol, often referred to as \"bad\" cholesterol. However, the role of vitamin E in heart health is still a topic of ongoing research.")))
                            .setSources(List.of(
                                    new PairEntity()
                                            .setKey("Nuts and Seeds")
                                            .setValue("Almonds, sunflower seeds, hazelnuts, and pine nuts are rich sources of vitamin E."),
                                    new PairEntity()
                                            .setKey("Vegetable Oils")
                                            .setValue("Sunflower oil, safflower oil, and wheat germ oil are high in vitamin E."),
                                    new PairEntity()
                                            .setKey("Leafy Greens")
                                            .setValue("Spinach, Swiss chard, and kale provide smaller amounts of vitamin E"),
                                    new PairEntity()
                                            .setKey("Fortified Foods")
                                            .setValue("Some breakfast cereals and other packaged foods are fortified with vitamin E.")
                            ))
                            .setMale(new DailyIntake()
                                    .setGender(Gender.MALE)
                                    .setLowerBound(new BigDecimal("15"))
                                    .setUpperBound(new BigDecimal("15"))
                                    .setMeasureUnit("milligrams (mg) of alpha-tocophero"))
                            .setFemale(new DailyIntake()
                                    .setGender(Gender.FEMALE)
                                    .setLowerBound(new BigDecimal("15"))
                                    .setUpperBound(new BigDecimal("15"))
                                    .setMeasureUnit("milligrams (mg) of alpha-tocophero"))
                    ,new VitaminEntity()
                            .setName("K")
                            .setDescription("Vitamin K is a fat-soluble vitamin that plays a crucial role in blood clotting, bone health, and other important bodily functions. There are two main forms of vitamin K: vitamin K1 (phylloquinone) and vitamin K2 (menaquinone)")
                            .setFunctions(List.of(
                                    new PairEntity()
                                            .setKey("Blood Clotting")
                                            .setValue("Vitamin K is essential for the synthesis of certain proteins that are involved in the blood clotting process. These proteins help stop bleeding by promoting the formation of blood clots when there is an injury."),
                                    new PairEntity()
                                            .setKey("Bone Health")
                                            .setValue("Vitamin K plays a role in bone health by assisting in the regulation of calcium. It helps ensure that calcium is properly deposited in bones and teeth, contributing to bone density and strength."),
                                    new PairEntity()
                                            .setKey("Heart Health")
                                            .setValue("Some research suggests that vitamin K might have a role in cardiovascular health by helping prevent the calcification of arteries and promoting overall heart health. However, more research is needed to fully understand this relationship."),
                                    new PairEntity()
                                            .setKey("Cell Growth and Regulation")
                                            .setValue("Vitamin K is involved in cellular processes related to growth, regulation, and apoptosis (programmed cell death).")))
                            .setSources(List.of(
                                    new PairEntity()
                                            .setKey("Vitamin K1 (Phylloquinone)")
                                            .setValue(" Found primarily in green leafy vegetables, such as spinach, kale, broccoli, and Brussels sprouts."),
                                    new PairEntity()
                                            .setKey("Vitamin K2 (Menaquinone)")
                                            .setValue("Produced by bacteria in the gut and also found in certain animal-based foods like dairy products, eggs, and meat. Fermented foods, such as natto (a type of fermented soybean), are particularly rich in vitamin K2.")
                            ))
                            .setMale(new DailyIntake()
                                    .setGender(Gender.MALE)
                                    .setLowerBound(new BigDecimal("120"))
                                    .setUpperBound(new BigDecimal("120"))
                                    .setMeasureUnit("micrograms (mcg)"))
                            .setFemale(new DailyIntake()
                                    .setGender(Gender.FEMALE)
                                    .setLowerBound(new BigDecimal("90"))
                                    .setUpperBound(new BigDecimal("90"))
                                    .setMeasureUnit("micrograms (mcg)"))
                    ,new VitaminEntity()
                            .setName("C")
                            .setDescription("Vitamin C, also known as ascorbic acid, is a water-soluble vitamin that is essential for a wide range of bodily functions. It's well-known for its antioxidant properties and its role in supporting the immune system.")
                            .setFunctions(List.of(
                                    new PairEntity()
                                            .setKey("Antioxidant Protection")
                                            .setValue("One of the primary roles of vitamin C is its antioxidant function. It helps protect cells from damage caused by harmful molecules called free radicals. Antioxidants like vitamin C play a key role in maintaining overall cellular health and reducing oxidative stress."),
                                    new PairEntity()
                                            .setKey("Immune System Support")
                                            .setValue("Vitamin C supports the immune system by promoting the production and function of white blood cells, which are vital for fighting infections. It may also help shorten the duration and severity of common colds."),
                                    new PairEntity()
                                            .setKey("Collagen Synthesis")
                                            .setValue("Vitamin C is essential for the synthesis of collagen, a protein that is important for the structure of skin, blood vessels, tendons, ligaments, and other connective tissues. It's crucial for wound healing and maintaining healthy skin."),
                                    new PairEntity()
                                            .setKey("Iron Absorption")
                                            .setValue("Vitamin C enhances the absorption of non-heme iron (the type of iron found in plant-based foods) from the digestive tract. This can be particularly important for individuals who follow vegetarian or vegan diets."),
                                    new PairEntity()
                                            .setKey("Neurotransmitter Production")
                                            .setValue("Vitamin C is involved in the synthesis of certain neurotransmitters, such as serotonin and norepinephrine, which play a role in mood regulation and the nervous system.")))
                            .setSources(List.of(
                                    new PairEntity()
                                            .setKey("Citrus Fruits")
                                            .setValue("Oranges,Grapefruits,Lemons,Limes,Tangerines"),
                                    new PairEntity()
                                            .setKey("Berries")
                                            .setValue("Strawberries,Blueberries,Raspberries,Blackberries"),
                                    new PairEntity()
                                            .setKey("")
                                            .setValue("Kiwi"),
                                    new PairEntity()
                                            .setKey("Bell Peppers")
                                            .setValue("Red bell peppers,Green bell peppers,Yellow bell peppers,Orange bell peppers"),
                                    new PairEntity()
                                            .setKey("Tropical Fruits")
                                            .setValue("Pineapple,Mango,Papaya"),
                                    new PairEntity()
                                            .setKey("Melons")
                                            .setValue("Cantaloupe,Watermelon"),
                                    new PairEntity()
                                            .setKey("Leafy Greens")
                                            .setValue("Spinach,Kale,Swiss chard"),
                                    new PairEntity()
                                            .setKey("Others")
                                            .setValue("Broccoli,Brussels Sprouts,Tomatoes,Cabbage,Cauliflower,Potatoes,Guava,Acerola Cherry,Mango,Papaya")
                            ))
                            .setMale(new DailyIntake()
                                    .setGender(Gender.MALE)
                                    .setLowerBound(new BigDecimal("90"))
                                    .setUpperBound(new BigDecimal("90"))
                                    .setMeasureUnit("milligrams (mg)"))
                            .setFemale(new DailyIntake()
                                    .setGender(Gender.FEMALE)
                                    .setLowerBound(new BigDecimal("75"))
                                    .setUpperBound(new BigDecimal("75"))
                                    .setMeasureUnit("milligrams (mg)"))
                    ,new VitaminEntity()
                            .setName("B1")
                            .setDescription("Vitamin B1, also known as thiamin, is a water-soluble B-vitamin that plays a vital role in energy metabolism and maintaining the health of the nervous system.")
                            .setFunctions(List.of(
                                    new PairEntity()
                                            .setKey("Energy Metabolism")
                                            .setValue("Thiamin is essential for converting carbohydrates into energy. It plays a key role in the metabolism of glucose, which is the primary source of energy for the body's cells."),
                                    new PairEntity()
                                            .setKey("Nervous System Health")
                                            .setValue("Thiamin is crucial for the proper functioning of the nervous system. It helps maintain the health of nerve cells and supports nerve impulse transmission, which is essential for muscle contractions and sensory functions."),
                                    new PairEntity()
                                            .setKey("Role in Coenzymes")
                                            .setValue("Thiamin is a precursor to thiamine pyrophosphate (TPP), a coenzyme involved in several biochemical reactions. TPP is necessary for reactions that generate energy from carbohydrates and certain amino acids."),
                                    new PairEntity()
                                            .setKey("Cardiovascular Health")
                                            .setValue("Thiamin is involved in the production of neurotransmitters and certain hormones, which can impact cardiovascular function and overall well-being.")))
                            .setSources(List.of(
                                    new PairEntity()
                                            .setKey("Whole grains")
                                            .setValue("brown rice, whole wheat, oats"),
                                    new PairEntity()
                                            .setKey("Legumes")
                                            .setValue("beans, lentils"),
                                    new PairEntity()
                                            .setKey("Nuts")
                                            .setValue("especially sunflower seeds"),
                                    new PairEntity()
                                            .setKey("Lean meats")
                                            .setValue("pork, beef, poultry"),
                                    new PairEntity()
                                            .setKey("Fish")
                                            .setValue("trout, mackerel"),
                                    new PairEntity()
                                            .setKey("-")
                                            .setValue("Eggs"),
                                    new PairEntity()
                                            .setKey("-")
                                            .setValue("Dairy products")
                            ))
                            .setMale(new DailyIntake()
                                    .setGender(Gender.MALE)
                                    .setLowerBound(new BigDecimal("1.2"))
                                    .setUpperBound(new BigDecimal("1.2"))
                                    .setMeasureUnit("milligrams (mg)"))
                            .setFemale(new DailyIntake()
                                    .setGender(Gender.FEMALE)
                                    .setLowerBound(new BigDecimal("1.1"))
                                    .setUpperBound(new BigDecimal("1.1"))
                                    .setMeasureUnit("milligrams (mg)"))
                    ,new VitaminEntity()
                            .setName("B2")
                            .setDescription("Vitamin B2, also known as riboflavin, is a water-soluble B-vitamin that plays a critical role in various cellular processes and energy production. It's involved in converting food into energy and is important for maintaining healthy skin, eyes, and red blood cells.")
                            .setFunctions(List.of(
                                    new PairEntity()
                                            .setKey("Energy Metabolism")
                                            .setValue("Riboflavin is an essential component of two coenzymes, flavin mononucleotide (FMN) and flavin adenine dinucleotide (FAD). These coenzymes are involved in numerous reactions that help convert carbohydrates, fats, and proteins from food into energy."),
                                    new PairEntity()
                                            .setKey("Antioxidant Protection")
                                            .setValue("Riboflavin is part of the antioxidant enzyme glutathione reductase, which helps maintain the body's antioxidant defenses. It assists in protecting cells from oxidative stress and damage caused by free radicals."),
                                    new PairEntity()
                                            .setKey("Skin and Eye Health")
                                            .setValue("Riboflavin contributes to maintaining healthy skin, eyes, and mucous membranes. It's essential for skin health and helps prevent conditions like cracked lips and skin lesions. It's also important for promoting good vision."),
                                    new PairEntity()
                                            .setKey("Red Blood Cell Formation")
                                            .setValue("Riboflavin plays a role in the synthesis of red blood cells, which are responsible for transporting oxygen throughout the body.")))
                            .setSources(List.of(
                                    new PairEntity()
                                            .setKey("Dairy products")
                                            .setValue("milk, yogurt, cheese"),
                                    new PairEntity()
                                            .setKey("Lean meats")
                                            .setValue("chicken, turkey, beef"),
                                    new PairEntity()
                                            .setKey("Fish")
                                            .setValue("salmon, trout"),
                                    new PairEntity()
                                            .setKey("-")
                                            .setValue("Eggs"),
                                    new PairEntity()
                                            .setKey("Leafy green vegetables")
                                            .setValue("spinach, broccoli"),
                                    new PairEntity()
                                            .setKey("Whole grains")
                                            .setValue("enriched cereals, whole wheat"),
                                    new PairEntity()
                                            .setKey("-")
                                            .setValue("Nuts and seeds")
                            ))
                            .setMale(new DailyIntake()
                                    .setGender(Gender.MALE)
                                    .setLowerBound(new BigDecimal("1.3"))
                                    .setUpperBound(new BigDecimal("1.3"))
                                    .setMeasureUnit("milligrams (mg)"))
                            .setFemale(new DailyIntake()
                                    .setGender(Gender.FEMALE)
                                    .setLowerBound(new BigDecimal("1.1"))
                                    .setUpperBound(new BigDecimal("1.1"))
                                    .setMeasureUnit("milligrams (mg)"))
                    , new VitaminEntity()
                            .setName("B3")
                            .setDescription("Vitamin B3, also known as niacin, is a water-soluble B-vitamin that plays a crucial role in energy metabolism, nervous system function, and maintaining healthy skin and digestive system. Niacin has two primary forms: nicotinic acid and nicotinamide (niacinamide), both of which are converted into coenzymes that participate in numerous biochemical reactions in the body.")
                            .setFunctions(List.of(
                                    new PairEntity()
                                            .setKey("Energy Metabolism")
                                            .setValue("Niacin is a component of the coenzymes nicotinamide adenine dinucleotide (NAD) and nicotinamide adenine dinucleotide phosphate (NADP), which are involved in energy production by assisting in the breakdown of carbohydrates, fats, and proteins."),
                                    new PairEntity()
                                            .setKey("Cell Signaling")
                                            .setValue("Niacin and its coenzymes play a role in cellular communication, including the transmission of signals within cells and between cells."),
                                    new PairEntity()
                                            .setKey("Skin Health")
                                            .setValue("Niacinamide (nicotinamide) has been shown to have benefits for skin health. It may help improve the appearance of skin by reducing inflammation, supporting collagen production, and maintaining the skin's natural barrier function."),
                                    new PairEntity()
                                            .setKey("Cardiovascular Health")
                                            .setValue("Niacin can help lower levels of LDL (\"bad\") cholesterol and triglycerides, while increasing levels of HDL (\"good\") cholesterol. However, the use of niacin supplements for this purpose is less common nowadays due to potential side effects."),
                                    new PairEntity()
                                            .setKey("Nervous System Support")
                                            .setValue("Niacin is essential for the health of the nervous system. It supports the function of nerve cells and contributes to the synthesis of neurotransmitters.")))
                            .setSources(List.of(
                                    new PairEntity()
                                            .setKey("Lean meats")
                                            .setValue("poultry, turkey, beef"),
                                    new PairEntity()
                                            .setKey("Fish")
                                            .setValue("tuna, salmon"),
                                    new PairEntity()
                                            .setKey("Dairy products")
                                            .setValue("milk, cheese"),
                                    new PairEntity()
                                            .setKey("-")
                                            .setValue("Eggs"),
                                    new PairEntity()
                                            .setKey("Legumes")
                                            .setValue("peanuts, lentils"),
                                    new PairEntity()
                                            .setKey("-")
                                            .setValue("Nuts and seeds"),
                                    new PairEntity()
                                            .setKey("-")
                                            .setValue("Fortified cereals and breads"),
                                    new PairEntity()
                                            .setKey("Whole grains")
                                            .setValue("brown rice, whole wheat")
                            ))
                            .setMale(new DailyIntake()
                                    .setGender(Gender.MALE)
                                    .setLowerBound(new BigDecimal("16"))
                                    .setUpperBound(new BigDecimal("16"))
                                    .setMeasureUnit("milligrams (mg)"))
                            .setFemale(new DailyIntake()
                                    .setGender(Gender.FEMALE)
                                    .setLowerBound(new BigDecimal("14"))
                                    .setUpperBound(new BigDecimal("14"))
                                    .setMeasureUnit("milligrams (mg)"))
                    ,new VitaminEntity()
                            .setName("B5")
                            .setDescription("Vitamin B5, also known as pantothenic acid, is a water-soluble B-vitamin that plays a crucial role in various metabolic processes in the body. It's involved in energy production, the synthesis of important molecules, and maintaining overall health.")
                            .setFunctions(List.of(
                                    new PairEntity()
                                            .setKey("Energy Metabolism")
                                            .setValue("Pantothenic acid is a component of coenzyme A (CoA), which is essential for the breakdown of carbohydrates, fats, and proteins to produce energy. CoA is a key player in the citric acid cycle (Krebs cycle), which is a fundamental part of energy production in cells."),
                                    new PairEntity()
                                            .setKey("Synthesis of Important Molecules")
                                            .setValue("CoA, derived from pantothenic acid, is also necessary for the synthesis of various molecules in the body. This includes the production of fatty acids, cholesterol, and acetylcholine (a neurotransmitter)."),
                                    new PairEntity()
                                            .setKey("Skin Health")
                                            .setValue("Pantothenic acid is often associated with skin health. It's used in various skincare products and supplements due to its potential benefits in reducing acne and promoting healthy skin."),
                                    new PairEntity()
                                            .setKey("Nervous System Function")
                                            .setValue("Pantothenic acid is involved in the production of acetylcholine, a neurotransmitter that is important for proper nerve transmission and muscle function.")))
                            .setSources(List.of(
                                    new PairEntity()
                                            .setKey("Meat")
                                            .setValue("chicken, beef, pork"),
                                    new PairEntity()
                                            .setKey("Fish")
                                            .setValue("salmon, tuna"),
                                    new PairEntity()
                                            .setKey("Dairy products")
                                            .setValue("milk, yogurt, cheese"),
                                    new PairEntity()
                                            .setKey("-")
                                            .setValue("Eggs"),
                                    new PairEntity()
                                            .setKey("Legumes")
                                            .setValue("lentils, chickpeas"),
                                    new PairEntity()
                                            .setKey("-")
                                            .setValue("Nuts and seeds"),
                                    new PairEntity()
                                            .setKey("Whole grains")
                                            .setValue("brown rice, whole wheat"),
                                    new PairEntity()
                                            .setKey("Vegetables")
                                            .setValue("broccoli, sweet potatoes")
                            ))
                            .setMale(new DailyIntake()
                                    .setGender(Gender.MALE)
                                    .setLowerBound(new BigDecimal("5"))
                                    .setUpperBound(new BigDecimal("5"))
                                    .setMeasureUnit("milligrams (mg)"))
                            .setFemale(new DailyIntake()
                                    .setGender(Gender.FEMALE)
                                    .setLowerBound(new BigDecimal("5"))
                                    .setUpperBound(new BigDecimal("5"))
                                    .setMeasureUnit("milligrams (mg)"))
                    ,new VitaminEntity()
                            .setName("B6")
                            .setDescription("Vitamin B6, also known as pyridoxine, is a water-soluble B-vitamin that plays a vital role in various metabolic processes in the body. It's involved in energy production, neurotransmitter synthesis, and the metabolism of amino acids and fatty acids.")
                            .setFunctions(List.of(
                                    new PairEntity()
                                            .setKey("Amino Acid Metabolism")
                                            .setValue("Vitamin B6 is necessary for the metabolism of amino acids, the building blocks of proteins. It helps convert amino acids into usable forms, which are crucial for the synthesis of proteins and other important molecules."),
                                    new PairEntity()
                                            .setKey("Neurotransmitter Production")
                                            .setValue("Vitamin B6 is involved in the synthesis of neurotransmitters such as serotonin, dopamine, and gamma-aminobutyric acid (GABA). These neurotransmitters play key roles in mood regulation, sleep, and overall brain function."),
                                    new PairEntity()
                                            .setKey("Hemoglobin Formation")
                                            .setValue("Vitamin B6 is required for the synthesis of hemoglobin, the protein in red blood cells that carries oxygen to cells and tissues throughout the body."),
                                    new PairEntity()
                                            .setKey("Immune System Function")
                                            .setValue("Vitamin B6 supports the immune system by influencing the production and activity of immune cells and antibodies."),
                                    new PairEntity()
                                            .setKey("Nerve Function")
                                            .setValue("Vitamin B6 is essential for proper nerve function and transmission of nerve signals.")))
                            .setSources(List.of(
                                    new PairEntity()
                                            .setKey("Meat")
                                            .setValue("chicken, turkey, beef"),
                                    new PairEntity()
                                            .setKey("Fish")
                                            .setValue("salmon, tuna"),
                                    new PairEntity()
                                            .setKey("Dairy products")
                                            .setValue("milk, cheese"),
                                    new PairEntity()
                                            .setKey("-")
                                            .setValue("Eggs"),
                                    new PairEntity()
                                            .setKey("Legumes")
                                            .setValue("lentils, beans"),
                                    new PairEntity()
                                            .setKey("-")
                                            .setValue("Nuts and seeds"),
                                    new PairEntity()
                                            .setKey("Whole grains")
                                            .setValue("brown rice, whole wheat"),
                                    new PairEntity()
                                            .setKey("-")
                                            .setValue("Fortified cereals")
                            ))
                            .setMale(new DailyIntake()
                                    .setGender(Gender.MALE)
                                    .setLowerBound(new BigDecimal("1.3"))
                                    .setUpperBound(new BigDecimal("1.7"))
                                    .setMeasureUnit("milligrams (mg)"))
                            .setFemale(new DailyIntake()
                                    .setGender(Gender.FEMALE)
                                    .setLowerBound(new BigDecimal("1.3"))
                                    .setUpperBound(new BigDecimal("1.5"))
                                    .setMeasureUnit(" milligrams (mg)"))
                    ,new VitaminEntity()
                            .setName("B7")
                            .setDescription("Vitamin B7, also known as biotin, is a water-soluble B-vitamin that plays a crucial role in various metabolic processes in the body. It's involved in the metabolism of carbohydrates, fats, and amino acids, and it supports healthy skin, hair, and nails. Biotin is also essential for maintaining healthy nerve function and assisting in the synthesis of certain enzymes.")
                            .setFunctions(List.of(
                                    new PairEntity()
                                            .setKey("Carbohydrate, Fat, and Protein Metabolism")
                                            .setValue("Biotin is a coenzyme that plays a key role in the metabolism of carbohydrates, fats, and amino acids. It helps convert these nutrients into energy that the body can use."),
                                    new PairEntity()
                                            .setKey("Healthy Skin, Hair, and Nails")
                                            .setValue("Biotin is often associated with promoting healthy skin, hair, and nails. It's a common ingredient in beauty supplements due to its potential role in maintaining the health and appearance of these tissues."),
                                    new PairEntity()
                                            .setKey("Nervous System Health")
                                            .setValue("Biotin is important for maintaining healthy nerve function and promoting the proper functioning of the nervous system."),
                                    new PairEntity()
                                            .setKey("Enzyme Synthesis")
                                            .setValue("Biotin is required for the synthesis of certain enzymes that are involved in various biochemical reactions in the body. These enzymes are crucial for processes such as fatty acid synthesis and gluconeogenesis.")))
                            .setSources(List.of(
                                    new PairEntity()
                                            .setKey("Organ meats")
                                            .setValue("liver, kidney"),
                                    new PairEntity()
                                            .setKey("-")
                                            .setValue("Eggs"),
                                    new PairEntity()
                                            .setKey("Dairy products")
                                            .setValue("milk, cheese"),
                                    new PairEntity()
                                            .setKey("-")
                                            .setValue("Nuts and seeds"),
                                    new PairEntity()
                                            .setKey("Legumes")
                                            .setValue("soybeans, peanuts"),
                                    new PairEntity()
                                            .setKey("Whole grains")
                                            .setValue("oats, barley"),
                                    new PairEntity()
                                            .setKey("-")
                                            .setValue("Leafy green vegetables"),
                                    new PairEntity()
                                            .setKey("-")
                                            .setValue("Fish")
                            ))
                            .setMale(new DailyIntake()
                                    .setGender(Gender.MALE)
                                    .setLowerBound(new BigDecimal("30"))
                                    .setUpperBound(new BigDecimal("30"))
                                    .setMeasureUnit("micrograms (mcg)"))
                            .setFemale(new DailyIntake()
                                    .setGender(Gender.FEMALE)
                                    .setLowerBound(new BigDecimal("30"))
                                    .setUpperBound(new BigDecimal("30"))
                                    .setMeasureUnit("micrograms (mcg)"))
                    ,new VitaminEntity()
                            .setName("B9")
                            .setDescription("Vitamin B9, also known as folate (or folic acid when in synthetic form), is a water-soluble B-vitamin that plays a crucial role in various bodily functions, including cell division, DNA synthesis, and the formation of red blood cells. It's particularly important during periods of rapid growth and development, such as pregnancy.")
                            .setFunctions(List.of(
                                    new PairEntity()
                                            .setKey("DNA Synthesis and Cell Division")
                                            .setValue("Folate is essential for the synthesis of DNA and RNA, the genetic material of cells. It plays a vital role in cell division and growth, making it particularly important during periods of rapid tissue development and growth."),
                                    new PairEntity()
                                            .setKey("Red Blood Cell Formation")
                                            .setValue("Folate is necessary for the formation of red blood cells. Adequate folate levels are crucial for preventing certain types of anemia, such as megaloblastic anemia."),
                                    new PairEntity()
                                            .setKey("Neural Tube Formation")
                                            .setValue("Folate is critical during early pregnancy for the proper formation of the neural tube in the developing fetus. Adequate folate intake can help prevent neural tube defects in newborns."),
                                    new PairEntity()
                                            .setKey("Homocysteine Regulation")
                                            .setValue("Folate, along with vitamins B6 and B12, helps regulate homocysteine levels in the blood. Elevated homocysteine levels are associated with an increased risk of cardiovascular disease."),
                                    new PairEntity()
                                            .setKey("")
                                            .setValue("")))
                            .setSources(List.of(
                                    new PairEntity()
                                            .setKey("Leafy green vegetables")
                                            .setValue("spinach, kale, lettuce"),
                                    new PairEntity()
                                            .setKey("Legumes")
                                            .setValue("beans, lentils"),
                                    new PairEntity()
                                            .setKey("-")
                                            .setValue("Fortified cereals and breads"),
                                    new PairEntity()
                                            .setKey("Citrus fruits")
                                            .setValue("oranges, lemons"),
                                    new PairEntity()
                                            .setKey("-")
                                            .setValue("Avocado"),
                                    new PairEntity()
                                            .setKey("-")
                                            .setValue("Eggs"),
                                    new PairEntity()
                                            .setKey("Liver")
                                            .setValue("organ meat")
                            ))
                            .setMale(new DailyIntake()
                                    .setGender(Gender.MALE)
                                    .setLowerBound(new BigDecimal("400"))
                                    .setUpperBound(new BigDecimal("400"))
                                    .setMeasureUnit("micrograms (mcg)"))
                            .setFemale(new DailyIntake()
                                    .setGender(Gender.FEMALE)
                                    .setLowerBound(new BigDecimal("400"))
                                    .setUpperBound(new BigDecimal("400"))
                                    .setMeasureUnit("micrograms (mcg)"))
                    ,new VitaminEntity()
                            .setName("B12")
                            .setDescription("Vitamin B12, also known as cobalamin, is a water-soluble B-vitamin that plays a critical role in various bodily functions, including red blood cell formation, nervous system health, and DNA synthesis. It's unique among the B-vitamins because it's not found in significant amounts in most plant-based foods and is often associated with animal products.")
                            .setFunctions(List.of(
                                    new PairEntity()
                                            .setKey("Red Blood Cell Formation")
                                            .setValue("Vitamin B12 is essential for the synthesis of red blood cells. It works in conjunction with folate to help prevent a type of anemia called megaloblastic anemia, where red blood cells are larger than normal."),
                                    new PairEntity()
                                            .setKey("Nervous System Health")
                                            .setValue("Vitamin B12 is crucial for maintaining the health of the nervous system. It helps protect nerve cells and supports nerve impulse transmission, which is vital for muscle function and sensory perception."),
                                    new PairEntity()
                                            .setKey("DNA Synthesis")
                                            .setValue("Vitamin B12 is involved in the synthesis of DNA, the genetic material of cells. It's particularly important during periods of rapid cell division, such as growth and development."),
                                    new PairEntity()
                                            .setKey("Homocysteine Regulation")
                                            .setValue("Vitamin B12, along with folate and vitamin B6, helps regulate homocysteine levels in the blood. Elevated homocysteine levels are associated with an increased risk of cardiovascular disease")))
                            .setSources(List.of(
                                    new PairEntity()
                                            .setKey("Meat")
                                            .setValue("beef, pork, poultry"),
                                    new PairEntity()
                                            .setKey("Fish")
                                            .setValue("salmon, trout, tuna"),
                                    new PairEntity()
                                            .setKey("Shellfish")
                                            .setValue("clams, mussels, crab"),
                                    new PairEntity()
                                            .setKey("Dairy products")
                                            .setValue("milk, cheese, yogurt"),
                                    new PairEntity()
                                            .setKey("-")
                                            .setValue("Eggs")
                            ))
                            .setMale(new DailyIntake()
                                    .setGender(Gender.MALE)
                                    .setLowerBound(new BigDecimal("2.4"))
                                    .setUpperBound(new BigDecimal("2.4"))
                                    .setMeasureUnit("micrograms (mcg)"))
                            .setFemale(new DailyIntake()
                                    .setGender(Gender.FEMALE)
                                    .setLowerBound(new BigDecimal("2.4"))
                                    .setUpperBound(new BigDecimal("2.4"))
                                    .setMeasureUnit("micrograms (mcg)"))
            ));
        }
    }

    public List<VitaminView> getVitamins() {
        return vitaminRepository.findAll().stream().map(VitaminView::new).toList();
    }

    public VitaminView getVitaminViewByName(String name) throws VitaminNotFound {
        return vitaminRepository.findByName(name)
                .map(VitaminView::new)
                .orElseThrow(() -> new VitaminNotFound(name, getAllVitaminsNames()));
    }

    public String getAllVitaminsNames(){
        return vitaminRepository.findAll()
                .stream()
                .map(VitaminEntity::getName)
                .collect(Collectors.joining(","));
    }
}
//,new VitaminEntity()
//                            .setName("")
//                            .setDescription("")
//                            .setFunctions(List.of(
//                                    new PairEntity()
//                                            .setKey("")
//                                            .setValue(""),
//                                    new PairEntity()
//                                            .setKey("")
//                                            .setValue(""),
//                                    new PairEntity()
//                                            .setKey("")
//                                            .setValue(""),
//                                    new PairEntity()
//                                            .setKey("")
//                                            .setValue(""),
//                                    new PairEntity()
//                                            .setKey("")
//                                            .setValue("")))
//                            .setSources(List.of(
//                                    new PairEntity()
//                                            .setKey("")
//                                            .setValue(""),
//                                    new PairEntity()
//                                            .setKey("")
//                                            .setValue(""),
//                                    new PairEntity()
//                                            .setKey("")
//                                            .setValue(""),
//                                    new PairEntity()
//                                            .setKey("")
//                                            .setValue("")
//                            ))
//                            .setMale(new DailyIntake()
//                                    .setGender(Gender.MALE)
//                                    .setLowerBound(new BigDecimal(""))
//                                    .setUpperBound(new BigDecimal(""))
//                                    .setMeasureUnit(""))
//                            .setFemale(new DailyIntake()
//                                    .setGender(Gender.FEMALE)
//                                    .setLowerBound(new BigDecimal(""))
//                                    .setUpperBound(new BigDecimal(""))
//                                    .setMeasureUnit(""))
