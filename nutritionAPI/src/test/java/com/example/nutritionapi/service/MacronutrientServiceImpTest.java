//package com.example.nutritionapi.service;
//
//import com.example.nutritionapi.domain.constants.entity.Macronutrient;
//import com.example.nutritionapi.domain.constants.entity.Pair;
//import com.example.nutritionapi.domain.dtos.viewDtos.MacronutrientView;
//import com.example.nutritionapi.exceptions.MacronutrientNotFoundException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.lang.reflect.Field;
//import java.util.*;
//import java.util.stream.Collectors;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class MacronutrientServiceImpTest {
//
//    @Mock
//    private Map<String, Macronutrient> macronutrientMap;
//
//    @InjectMocks
//    private MacronutrientServiceImp macronutrientServiceImp;
//
//    private final List<Macronutrient> macronutrientList = new ArrayList<>();
//    private final List<MacronutrientView> macronutrientViews = new ArrayList<>();
//
//    @BeforeEach
//    void setUp() throws NoSuchFieldException, IllegalAccessException {
//
//        Field field = macronutrientServiceImp.getClass().getDeclaredField("macronutrientMap");
//        field.setAccessible(true);
//        field.set(macronutrientServiceImp, macronutrientMap);
//
//        macronutrientList.add(new Macronutrient()
//                .setName("Carbohydrates1")
//                .setDescription("The amount of carbohydrates you should consume depends on various factors, including your age, gender, activity level, health goals, and individual metabolic needs. Carbohydrate needs can vary widely from person to person.")
//                .setTypes(
//                        List.of(
//                                new Pair()
//                                        .setKey("Simple Carbohydrates")
//                                        .setValue("These are made up of one or two sugar molecules. Simple carbs are found in foods like fruits (fructose), milk (lactose), and table sugar (sucrose). They are quickly digested and can lead to rapid spikes in blood sugar levels."),
//                                new Pair()
//                                        .setKey("Complex Carbohydrates")
//                                        .setValue("These consist of longer chains of sugar molecules. They are found in foods like whole grains (e.g., brown rice, quinoa, oats), legumes (beans, lentils), and vegetables. Complex carbs are broken down more slowly, providing sustained energy and helping to stabilize blood sugar levels.")
//                        ))
//                .setFunctions(
//                        List.of(
//                                new Pair()
//                                        .setKey("Energy Source")
//                                        .setValue("Carbohydrates are the body's primary and most efficient source of energy. When you consume carbs, they are broken down into glucose, which is used by cells for fuel."),
//                                new Pair()
//                                        .setKey("Brain Function")
//                                        .setValue("The brain relies heavily on glucose for energy. Consuming adequate carbohydrates helps support cognitive function, memory, and mood."),
//                                new Pair()
//                                        .setKey("Muscle Glycogen")
//                                        .setValue("Carbohydrates are stored in the muscles and liver as glycogen. During exercise, muscle glycogen serves as a readily available source of energy."),
//                                new Pair()
//                                        .setKey("Preventing Protein Breakdown")
//                                        .setValue("Adequate carbohydrate intake spares protein from being used as an energy source. This is important for preserving muscle mass.")
//                        ))
//                .setSources(
//                        List.of(
//                                new Pair()
//                                        .setKey("Grains")
//                                        .setValue("Whole grains like brown rice, quinoa, whole wheat, and oats provide complex carbohydrates, fiber, and various nutrients."),
//                                new Pair()
//                                        .setKey("Fruits")
//                                        .setValue("Fruits contain natural sugars along with vitamins, minerals, and fiber. Berries, apples, citrus fruits, and bananas are popular choices."),
//                                new Pair()
//                                        .setKey("Vegetables")
//                                        .setValue("Starchy vegetables (potatoes, corn) and non-starchy vegetables (leafy greens, broccoli, peppers) are rich in carbs, fiber, vitamins, and minerals."),
//                                new Pair()
//                                        .setKey("Legumes")
//                                        .setValue("Beans, lentils, chickpeas, and other legumes offer both carbohydrates and protein."),
//                                new Pair()
//                                        .setKey("Dairy")
//                                        .setValue("Dairy products contain lactose, a natural sugar. Choose low-fat or non-fat options for reduced saturated fat.")
//                        ))
//                .setDietaryConsiderations(
//                        List.of(
//                                new Pair()
//                                        .setKey("Fiber")
//                                        .setValue("Many carbohydrate-rich foods are also high in dietary fiber, which aids digestion, supports a healthy gut, and helps control appetite."),
//                                new Pair()
//                                        .setKey("Glycemic Index (GI)")
//                                        .setValue("The GI measures how quickly a carbohydrate-containing food raises blood sugar levels. Foods with a low GI (complex carbs) lead to gradual increases in blood sugar, while high-GI foods (simple carbs) cause rapid spikes."),
//                                new Pair()
//                                        .setKey("Added Sugars")
//                                        .setValue("Be mindful of added sugars in processed foods and sugary beverages. Consuming too much added sugar can lead to health issues.")
//                        ))
//                .setActiveState(0.50)
//                .setInactiveState(0.50)
//        );
//        macronutrientList.add(
//                new Macronutrient()
//                        .setName("Carbohydrates2")
//                        .setDescription("The amount of carbohydrates you should consume depends on various factors, including your age, gender, activity level, health goals, and individual metabolic needs. Carbohydrate needs can vary widely from person to person.")
//                        .setTypes(
//                                List.of(
//                                        new Pair()
//                                                .setKey("Simple Carbohydrates")
//                                                .setValue("These are made up of one or two sugar molecules. Simple carbs are found in foods like fruits (fructose), milk (lactose), and table sugar (sucrose). They are quickly digested and can lead to rapid spikes in blood sugar levels."),
//                                        new Pair()
//                                                .setKey("Complex Carbohydrates")
//                                                .setValue("These consist of longer chains of sugar molecules. They are found in foods like whole grains (e.g., brown rice, quinoa, oats), legumes (beans, lentils), and vegetables. Complex carbs are broken down more slowly, providing sustained energy and helping to stabilize blood sugar levels.")
//                                ))
//                        .setFunctions(
//                                List.of(
//                                        new Pair()
//                                                .setKey("Energy Source")
//                                                .setValue("Carbohydrates are the body's primary and most efficient source of energy. When you consume carbs, they are broken down into glucose, which is used by cells for fuel."),
//                                        new Pair()
//                                                .setKey("Brain Function")
//                                                .setValue("The brain relies heavily on glucose for energy. Consuming adequate carbohydrates helps support cognitive function, memory, and mood."),
//                                        new Pair()
//                                                .setKey("Muscle Glycogen")
//                                                .setValue("Carbohydrates are stored in the muscles and liver as glycogen. During exercise, muscle glycogen serves as a readily available source of energy."),
//                                        new Pair()
//                                                .setKey("Preventing Protein Breakdown")
//                                                .setValue("Adequate carbohydrate intake spares protein from being used as an energy source. This is important for preserving muscle mass.")
//                                ))
//                        .setSources(
//                                List.of(
//                                        new Pair()
//                                                .setKey("Grains")
//                                                .setValue("Whole grains like brown rice, quinoa, whole wheat, and oats provide complex carbohydrates, fiber, and various nutrients."),
//                                        new Pair()
//                                                .setKey("Fruits")
//                                                .setValue("Fruits contain natural sugars along with vitamins, minerals, and fiber. Berries, apples, citrus fruits, and bananas are popular choices."),
//                                        new Pair()
//                                                .setKey("Vegetables")
//                                                .setValue("Starchy vegetables (potatoes, corn) and non-starchy vegetables (leafy greens, broccoli, peppers) are rich in carbs, fiber, vitamins, and minerals."),
//                                        new Pair()
//                                                .setKey("Legumes")
//                                                .setValue("Beans, lentils, chickpeas, and other legumes offer both carbohydrates and protein."),
//                                        new Pair()
//                                                .setKey("Dairy")
//                                                .setValue("Dairy products contain lactose, a natural sugar. Choose low-fat or non-fat options for reduced saturated fat.")
//                                ))
//                        .setDietaryConsiderations(
//                                List.of(
//                                        new Pair()
//                                                .setKey("Fiber")
//                                                .setValue("Many carbohydrate-rich foods are also high in dietary fiber, which aids digestion, supports a healthy gut, and helps control appetite."),
//                                        new Pair()
//                                                .setKey("Glycemic Index (GI)")
//                                                .setValue("The GI measures how quickly a carbohydrate-containing food raises blood sugar levels. Foods with a low GI (complex carbs) lead to gradual increases in blood sugar, while high-GI foods (simple carbs) cause rapid spikes."),
//                                        new Pair()
//                                                .setKey("Added Sugars")
//                                                .setValue("Be mindful of added sugars in processed foods and sugary beverages. Consuming too much added sugar can lead to health issues.")
//                                ))
//                        .setActiveState(0.50)
//                        .setInactiveState(0.50)
//        );
//        //TODO: FIX THAT
////        macronutrientViews.add(new MacronutrientView(macronutrientList.get(0)));
////        macronutrientViews.add(new MacronutrientView(macronutrientList.get(1)));
//    }
//
//    @Test
//    void getAllMacrosView_convertIntoView_Successful() {
//        when(macronutrientMap.values()).thenReturn(macronutrientList);
//
//        List<MacronutrientView> result = macronutrientServiceImp.getAllMacrosView();
//
//        assertEquals(macronutrientViews, result);
//    }
//
//    @Test
//    void getMacroViewByName_validName_successful() throws MacronutrientNotFoundException {
//        String VALID_NAME = "valid";
//        when(macronutrientMap.containsKey(VALID_NAME)).thenReturn(true);
//        when(macronutrientMap.get(VALID_NAME)).thenReturn(macronutrientList.get(0));
//
//        MacronutrientView result = macronutrientServiceImp.getMacroViewByName(VALID_NAME);
//        MacronutrientView expected = macronutrientViews.get(0);
//
//        assertEquals(expected, result);
//    }
//
//    @Test
//    void getMacroViewByName_invalidName_throwsMacronutrientNotFoundException() {
//        String INVALID_NAME = "invalid";
//        when(macronutrientMap.containsKey(INVALID_NAME)).thenReturn(false);
//
//
//        MacronutrientNotFoundException exception = assertThrows(MacronutrientNotFoundException.class, () -> macronutrientServiceImp.getMacroViewByName(INVALID_NAME));
//
//        assertEquals(exception.getMessage(), INVALID_NAME);
//    }
//
//    @Test
//    void getAllMacrosNames_getAllName_successful() {
//        when(macronutrientMap.keySet()).thenReturn(macronutrientList
//                .stream()
//                .map(Macronutrient::getName)
//                .collect(Collectors.toCollection(LinkedHashSet::new)));
//
//        String result = macronutrientServiceImp.getAllMacrosNames();
//        String expected = "Carbohydrates1,Carbohydrates2";
//
//        assertEquals(expected , result);
//    }
//
//    @Test
//    void findAll() {
//        when(macronutrientMap.values()).thenReturn(macronutrientList);
//
//        List<Macronutrient> result = macronutrientServiceImp.findAll();
//
//        assertEquals(macronutrientList , result);
//    }
//}