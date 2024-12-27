//package com.example.nutritionapi.service;
//
//import com.example.nutritionapi.feature.macros.entity.Macronutrient;
//import com.example.nutritionapi.feature.shared.entity.Pair;
//import com.example.nutritionapi.feature.vitamin.entity.Vitamin;
//import com.example.nutritionapi.feature.macros.dtos.MacronutrientView;
//import com.example.nutritionapi.feature.vitamin.dto.VitaminView;
//import com.example.nutritionapi.infrastructure.exceptions.VitaminNotFoundException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.lang.reflect.Field;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.LinkedHashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class VitaminServiceImpTest {
//
//    @Mock
//    private Map<String, Vitamin> vitaminMap;
//
//    @InjectMocks
//    private VitaminServiceImp vitaminServiceImp;
//
//    private final List<Vitamin> vitamins = new ArrayList<>();
//    private final List<VitaminView> vitaminViews = new ArrayList<>();
//
//    @BeforeEach
//    void setUp() throws NoSuchFieldException, IllegalAccessException {
//
//        Field field = vitaminServiceImp.getClass().getDeclaredField("vitaminMap");
//        field.setAccessible(true);
//        field.set(vitaminServiceImp, vitaminMap);
//
//        vitamins.add(
//                new Vitamin()
//                        .setName("A")
//                        .setDescription("Vitamin A is a fat-soluble vitamin that plays a critical role in various bodily functions, including vision, immune system support, and maintaining healthy skin. It exists in several forms, including retinol, retinal, and retinoic acid, which have distinct functions in the body.")
//                        .setFunctions(List.of(
//                                new Pair()
//                                        .setKey("Vision")
//                                        .setValue(" Vitamin A is essential for maintaining good vision. It is a component of rhodopsin, a protein in the retina that allows the eyes to adjust to changes in light and facilitates vision in low-light conditions. A deficiency in vitamin A can lead to night blindness and other visual impairments."),
//                                new Pair()
//                                        .setKey("Immune System Support")
//                                        .setValue("Vitamin A helps maintain the integrity of the mucosal surfaces, such as the linings of the respiratory, digestive, and urinary tracts. This is important for preventing infections and supporting immune responses."),
//                                new Pair()
//                                        .setKey("Skin Health")
//                                        .setValue("Vitamin A plays a role in skin health by promoting the production of skin cells and supporting the health of mucous membranes. It's often used in skincare products for its potential to improve skin texture and reduce signs of aging."),
//                                new Pair()
//                                        .setKey("Cell Differentiation")
//                                        .setValue("Vitamin A is involved in the process of cell differentiation, which is crucial for normal growth and development, especially during embryonic development.")))
//                        .setSources(List.of(
//                                new Pair()
//                                        .setKey("Preformed Vitamin A (Retinoids)")
//                                        .setValue("Found in animal products such as liver, fish, eggs, and dairy. These foods contain retinol, which is a form of preformed vitamin A."),
//                                new Pair()
//                                        .setKey("Provitamin A Carotenoids")
//                                        .setValue(" Found in plant-based foods, primarily in the form of beta-carotene. Beta-carotene is a pigment that gives fruits and vegetables their orange, red, and yellow colors. The body can convert provitamin A carotenoids into active vitamin A as needed. Foods rich in beta-carotene include carrots, sweet potatoes, spinach, kale, and butternut squash.")
//                        ))
//                        .setMaleLowerBoundIntake(new BigDecimal("700"))
//                        .setMaleHigherBoundIntake(new BigDecimal("900"))
//                        .setFemaleLowerBoundIntake(new BigDecimal("600"))
//                        .setFemaleHigherBoundIntake(new BigDecimal("700"))
//                        .setMeasure("micrograms of retinol activity equivalents (RAE)")
//        );
//
//
//        vitamins.add(
//                new Vitamin()
//                        .setName("D")
//                        .setDescription("Vitamin D is a fat-soluble vitamin that is essential for several important functions in the body. Often referred to as the \"sunshine vitamin,\" it can be synthesized by the skin when exposed to sunlight. Vitamin D also comes from dietary sources and supplements. Here's more information about vitamin D")
//                        .setFunctions(List.of(
//                                new Pair()
//                                        .setKey("Bone Health")
//                                        .setValue("One of the primary functions of vitamin D is to regulate calcium and phosphorus absorption in the intestines. This is crucial for maintaining strong and healthy bones. Vitamin D helps ensure that calcium is properly deposited in bones, which is important for preventing conditions like osteoporosis and fractures."),
//                                new Pair()
//                                        .setKey("Immune System Support")
//                                        .setValue("Vitamin D plays a role in modulating the immune system. It helps regulate immune responses and supports the body's defense mechanisms against infections."),
//                                new Pair()
//                                        .setKey("Cell Growth and Differentiation")
//                                        .setValue("Vitamin D is involved in cell growth, replication, and differentiation. It is important for the normal development of cells and tissues."),
//                                new Pair()
//                                        .setKey("Hormone Regulation")
//                                        .setValue("Vitamin D acts as a hormone in the body, influencing various physiological processes. It has been linked to the regulation of insulin secretion, blood pressure, and the functioning of the cardiovascular system.")))
//                        .setSources(List.of(
//                                new Pair()
//                                        .setKey("Sunlight")
//                                        .setValue("The skin can produce vitamin D when exposed to ultraviolet B (UVB) sunlight. However, factors like skin color, geographical location, time of day, and sunscreen use can affect the body's ability to synthesize vitamin D from sunlight."),
//                                new Pair()
//                                        .setKey("Dietary Sources")
//                                        .setValue("While there are fewer natural dietary sources of vitamin D, some foods are fortified with it. Dietary sources include fatty fish (salmon, mackerel, sardines), cod liver oil, egg yolks, and fortified foods like milk, orange juice, and cereal.")
//                        ))
//                        .setMaleLowerBoundIntake(new BigDecimal("600"))
//                        .setMaleHigherBoundIntake(new BigDecimal("800"))
//                        .setFemaleLowerBoundIntake(new BigDecimal("600"))
//                        .setFemaleHigherBoundIntake(new BigDecimal("800"))
//                        .setMeasure("international units (IU)")
//
//        );
//        //TODO: FIX THAT
////        vitaminViews.add(new VitaminView(vitamins.get(0)));
////        vitaminViews.add(new VitaminView(vitamins.get(1)));
//
//    }
//
//    @Test
//    void getVitamins_convertToView_successful() {
//        when(vitaminMap.values()).thenReturn(vitamins);
//
//        List<VitaminView> result = vitaminServiceImp.getVitamins();
//
//        assertEquals(vitaminViews , result);
//    }
//
//    @Test
//    void getVitaminViewByName_validName_successful() throws VitaminNotFoundException {
//        String VALID_NAME = "valid";
//        when(vitaminMap.containsKey(VALID_NAME)).thenReturn(true);
//        when(vitaminMap.get(VALID_NAME)).thenReturn(vitamins.get(0));
//
//        VitaminView result = vitaminServiceImp.getVitaminViewByName(VALID_NAME);
//        VitaminView expected = vitaminViews.get(0);
//
//        assertEquals(expected, result);
//    }
//    @Test
//    void getVitaminViewByName_invalidName_throwsVitaminNotFoundException(){
//
//        String INVALID_NAME = "valid";
//        when(vitaminMap.containsKey(INVALID_NAME)).thenReturn(false);
//
//        assertThrows(VitaminNotFoundException.class,
//                () ->vitaminServiceImp.getVitaminViewByName(INVALID_NAME));
//
//    }
//
//    @Test
//    void getAllVitaminsNames_correctNames_Successful() {
//        when(vitaminMap.keySet()).thenReturn(vitamins
//                .stream()
//                .map(Vitamin::getName)
//                .collect(Collectors.toCollection(LinkedHashSet::new)));
//
//        String result = vitaminServiceImp.getAllVitaminsNames();
//        String expected = "A,D";
//
//        assertEquals(expected , result);
//    }
//
//    @Test
//    void findAll_getAll_successful() {
//        when(vitaminMap.values()).thenReturn(vitamins);
//
//        List<Vitamin> result = vitaminServiceImp.findAll();
//
//        assertEquals(vitamins , result);
//    }
//}