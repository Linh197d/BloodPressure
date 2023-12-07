package blood.pressure.fingerprint.scanner.bpmonitor.data

class InfoText {
    companion object {
        var listKnow: MutableList<Info> = ArrayList()
        var listLearn: MutableList<Info> = ArrayList()
        var listFind: MutableList<Info> = ArrayList()
        var listBreak: MutableList<Info> = ArrayList()
        var listType: MutableList<Info> = ArrayList()
        var listNotice: MutableList<Info> = ArrayList()
        var listProblem: MutableList<Info> = ArrayList()
        var listUnderstand: MutableList<Info> = ArrayList()
        var listDrugsHyper: MutableList<Info> = ArrayList()
        var listControl: MutableList<Info> = ArrayList()
        var listLower: MutableList<Info> = ArrayList()
        var listDiagnose: MutableList<Info> = ArrayList()
        var listDrugsHypo: MutableList<Info> = ArrayList()
        var listTipsHyper: MutableList<Info> = ArrayList()
        var listTipsHypo: MutableList<Info> = ArrayList()

        init {
            listKnow.add(
                Info(
                    "",
                    "What range can be determined as hypertension? If one of your readings reaches the standard for high blood pressure, but the other one does not, how to determine that? To define your blood pressure numbers, you can see blood pressure category below."
                )
            )
            listKnow.add(Info("header", ""))
            listKnow.add(
                Info(
                    "1. Hypotension",
                    "If your readings are less than 90/60 mmHg, you are probably hypotensive. You do not need any treatment as low blood pressure causes no harm and no symptoms usually. However, when your blood pressure often drops suddenly over 20 mmHg, falls suddenly due to some medications, or you feel dizzy, fainting, fatigue, etc.,you ought to seek treatment."
                )
            )
            listKnow.add(
                Info(
                    "2. Normal Range",
                    "If your readings are higher than 90/60 mmHg and less than 120/80 mmHg, your blood pressure is normal. You need to maintain or adopt a healthy lifestyle to prevent hypertension from developing.${
                        System.getProperty(
                            "line.separator"
                        )
                    } ${System.getProperty("line.separator")}Besides, if you have any family members who have hypertension, it is recommended to be even more mindful of your lifestyle as your risk of developing hypertension can be high."
                )
            )
            listKnow.add(
                Info(
                    "3. Elevated",
                    "When your blood pressure is higher than the normal one but doesn\\'t reach 130/80 mmHg, your BP is considered elevated. If your lifestyle and eating habits are not improved, you are very likely to develop high blood pressure."
                )
            )
            listKnow.add(
                Info(
                    "4. Hypertension Stage 1",
                    "Consistent systolic pressure readings between 130 to 139 mmHg or diastolic pressure readings between 80 to 89 mmHg means you are at hypertension stage 1.${
                        System.getProperty(
                            "line.separator"
                        )
                    } ${System.getProperty("line.separator")}Generally speaking, you only need to improve your lifestyle to control blood pressure. However, if you have a high risk of cardiovascular disease (heart disease, stroke, etc.), you need to start taking medicine accordingly."
                )
            )
            listKnow.add(
                Info(
                    "5. Hypertension Stage 2",
                    "Hypertension stage 2 is when you have consistent systolic pressure readings between 140 to 180 mmHg or diastolic pressure readings between 90 to 120 mmHg.${
                        System.getProperty(
                            "line.separator"
                        )
                    } ${System.getProperty("line.separator")}At this stage, you should combine lifestyle changes and one or more medications. After the first month of treatment and lifestyle improvement, if your blood pressure is under control, you can go to the hospital for another measurement in 3 to 6 months. If the blood pressure is higher or does not change, you need to consult a doctor to change to other treatments."
                )
            )
            listKnow.add(
                Info(
                    "6. Hypertensive Crisis",
                    "If you are measuring your blood pressure at home and see your systolic reading over 180 mmHg or diastolic number above 120 mmHg, you need to calm down at first then wait a few minutes to retest it. If you are still in the range of the hypertension crisis, don\\'t hesitate to call the emergency service or go to the hospital right now."
                )
            )
            //
            listLearn.add(
                Info(
                    "",
                    "Whether you measure your blood pressure at home or in the hospital, you must be puzzled what does blood pressure mean or by the two columns of numbers on the device screen. Don't worry, after reading this article, all will be answered."
                )
            )
            listLearn.add(
                Info(
                    "What is blood pressure?",
                    "The heart is like a water pump, continuously pumping blood to the blood vessels throughout the body. When blood runs, it pushes against the walls of blood vessels. The strength of pushing is namely blood pressure."
                )
            )
            listLearn.add(
                Info(
                    "What are two numbers on the BP device?",
                    "Two readings on your blood pressure monitor represent systolic blood pressure and diastolic blood pressure. The top or the first number is systolic BP, meaning the amount of pressure in the arteries when your heart pushes blood through arteries. The bottom or the second number is the diastolic one, referring to the pressure in arteries when your heart is between beats."
                )
            )
            //
            listFind.add(
                Info(
                    "Hypertension",
                    "The human cardiovascular system seems like a water circulatory system, in which blood circulates constantly like water and transports oxygen and nutrition to organs and blood vessels.${
                        System.getProperty("line.separator")
                    } ${
                        System.getProperty("line.separator")
                    }Blood flow becomes difficult when blood vessels lose their elasticity . At that time, your body tries to increase the strength of pushing to make blood flows, which causes hypertension."
                )
            )
            listFind.add(
                Info(
                    "│Hypotension",
                    "If your blood pressure consistently lowers 90/60 mmHg or falls suddenly over 20 mmHg, you should keep an eye on your blood pressure. Hypotension with no symptoms usually doesn't need treatment. Nevertheless, accurate diagnosis and categorized therapies are needed if symptoms or sudden fail emerges."
                )
            )
            //
            listBreak.add(
                Info(
                    "",
                    "You may hear all kinds of myths about blood pressure. Believing in these myths can really make your blood pressure control vain or even make your situation worse. Below are 7 common misunderstands concerning blood pressure. Check out the following and stop believing them."
                )
            )
            listBreak.add(
                Info(
                    "1. No worries if I have the low blood pressure",
                    "Generally speaking, if you have hypotension, some small lifestyle changes can solve the problem. However, if you experience a sudden fall of over 20 mmHg or feel fainting, fatigue, blurred vision, etc., you are in need of professional treatment. If not treated immediately, the hypotension can be life-threatening."
                )
            )
            listBreak.add(
                Info(
                    "2. I don't have hypertension if no symptoms emerge",
                    "Do you know that hypertension is called the silent killer? It often runs silent and may have no symptoms, or the symptoms may be mild, which will not be taken seriously while it is causing great damage to your health.${
                        System.getProperty("line.separator")
                    } ${
                        System.getProperty("line.separator")
                    }When the symptoms appear, it indicates that the heart, brain, kidney, blood vessel or other organs have been severely damaged and cannot maintain normal functions. At that time, the optimal treatment opportunity has been missed. Meanwhile, complications are developed and life is under threat."
                )
            )
            listBreak.add(
                Info(
                    "3. High blood pressure can be cured",
                    "Only secondary hypertension can be cured as it has a clear cause. When the secondary condition is cured, secondary hypertension will not last anymore.${
                        System.getProperty("line.separator")
                    } ${
                        System.getProperty("line.separator")
                    }Primary hypertension cannot be healed yet, but it is possible to prevent or decrease its harm to the body by controlling blood pressure to normal through medications or improving lifestyle. The claim that high blood pressure can be cured by simple methods and never relapse is deceptive."
                )
            )
            listBreak.add(
                Info(
                    "4. Drinking red wine benefits blood pressure control",
                    "Myth has it that resveratrol inside the red wine can maintain cardiac health. However, no study can prove that statement. If the resveratrol really could protect your cardiac health, you need to drink an excessive amount of red wine to achieve the effect, which could harm your body badly. Besides, alcohol has been proven to be of no benefit to the cardiovascular system."
                )
            )
            listBreak.add(
                Info(
                    "5. I can stop taking medicines if my blood pressure is under control",
                    "This is totally wrong and can be misleading. Primary hypertension can never be cured and most patients need to take medication all their lives. Your blood pressure under control is the result of drug control, not that high blood pressure has been cured. Once the drug is stopped, blood pressure is likely to rebound. Moreover, quit taking certain drugs will cause serious withdrawal reactions.${
                        System.getProperty(
                            "line.separator"
                        )
                    } ${System.getProperty("line.separator")}So even if your blood pressure has been stable for a long time, your medication should not be changed or stopped without proper guidance. Consult the doctor before making any changes to the medication Vou should reduce the amount of medication under your doctor's guidance, monitor blood pressure regularly, and maintain a healthy lifestyle."
                )
            )
            listBreak.add(
                Info(
                    "6. Young people will not get hypertension",
                    "Although patients with hypertension are common among middle-aged and elderly people, young people can also suffer from it. The level of blood pressure is affected by age, body size, level of sexual development, lifestyle, etc."
                )
            )
            listBreak.add(
                Info(
                    "7. Only men can develop hypertension",
                    "The risk difference between men and women actually depends on age. Before the age of 45, men are at greater risk of hypertension than women. Between the ages of 45 and 64, they have almost the same risk. It is worth noting that after 64 years old, women's risk of getting high blood pressure is higher than men's."
                )
            )
            //
            listType.add(
                Info(
                    "",
                    "Do you know there are two main types of high blood pressure? One is primary and another one is secondary high blood pressure. They have different causes and let's read on to know more about them."
                )
            )
            listType.add(
                Info(
                    "Primary Hypertension",
                    "The most common hypertension is the primary type. It means a consistently high blood pressure not related to another medical condition. Despite years of research on hypertension, a specific cause isn't known. Unhealthy diets, physical inactivity, consumption of tobacco and alcohol, being overweight or obese, etc. could elevate your blood pressure.${
                        System.getProperty("line.separator")
                    } ${System.getProperty("line.separator")}Patients' blood pressure will rise as age increases. The curing method has not been found until now. Therefore, most patients need to take medication to control blood pressure all life long."
                )
            )
            listType.add(
                Info(
                    "Secondary Hypertension",
                    "Only about 5 to 10 percent of hypertension is the secondary type. Besides, it is proved that younger people are likely to have secondary hypertension.${
                        System.getProperty(
                            "line.separator"
                        )
                    } ${System.getProperty("line.separator")}Secondary hypertension is caused by certain medications such as birth control pills, etc., or various conditions like obstructive sleep apnea, kidney disease, adrenal gland tumors, thyroid problems, blood vessel defects, etc. When patients stop taking the medicine or cure the secondary condition, secondary hypertension will get better."
                )
            )
            //
            listNotice.add(
                Info(
                    "1. Headache",
                    "Headache led by high blood pressure usually indicates an emergency. When the blood pressure is too high, it can damage the blood vessels of your brain, leading to an increase in intracranial pressure. You will have pulsating headaches on both sides of your head and any activity will make the headache worse."
                )
            )
            listNotice.add(
                Info(
                    "2. Dizziness",
                    "Although dizziness may be a side effect of taking anti hypertensive medication, you should not take it for granted. Severe high blood pressure is a major cause of stroke. Sudden dizziness, loss of balance or unsteady walking can all be a sign of stroke."
                )
            )
            listNotice.add(
                Info(
                    "3. Nausea",
                    "If you are a person with hypertension and experience sudden nausea and loss of appetite, you may come across a hypertensive crisis. Nausea caused by extremely high blood pressure usually happens together with dizziness."
                )
            )
            listNotice.add(
                Info(
                    "4. Shortness of breath",
                    "High blood pressure will affect heart and lung function, leading to shortness of breath. When patients exercise, that could be more noticeable."
                )
            )
            listNotice.add(
                Info(
                    "5. Chest pain",
                    "Hypertension is a leading risk factor for heart attack and heart failure. High blood pressure hardens arteries, bringing difficult blood flow and oxygen delivery. It means less blood reaching the heart and eventually invoking chest pain, also called angina."
                )
            )
            listNotice.add(
                Info(
                    "6. Blurred vision",
                    "There are many small vessels on your eyeballs. When your blood pressure rises to a dangerous level, these vessels can be damaged to bleed, which causes blurred vision or even loss of vision."
                )
            )
            listNotice.add(
                Info(
                    "7. Nosebleed",
                    "Hypertension usually doesn't cause nosebleed unless your blood pressure is extremely high. The bleeding means vessels inside your nose are damaged. At this point, you need to call an ambulance or go to the hospital immediately."
                )
            )
            //
            listProblem.add(
                Info(
                    "",
                    "High blood pressure is a big threat to your health. It can steadily damage your heart, brain, kidney and eyes with no symptoms. If you want to know hypertension's effects on your body, read below to get a clear view."
                )
            )
            listProblem.add(
                Info(
                    "1. Hypertension leads to heart diseases",
                    "High blood pressure can cause coronary heart disease, heart failure, etc. Some studies illustrate that about a quarter of heart failure cases are caused by high blood pressure.${
                        System.getProperty("line.separator")
                    } ${System.getProperty("line.separator")}Hypertension will make arteries less flexible and cause fatty substances to block blood vessels, which indicates smooth blood flow to the heart becomes impossible. At that time, the heart needs to make the left ventricle work harder and pump blood to various organs.${
                        System.getProperty(
                            "line.separator"
                        )
                    } ${System.getProperty("line.separator")}Eventually, the extra workload will cause the left ventricle to expand, which greatly increases the risk of developing heart diseases. On the other hand, the heart becomes weak and is not able to pump blood, leading to heart failure."
                )
            )
            listProblem.add(
                Info(
                    "2. Hypertension can damage your brain",
                    "High blood pressure can cause transient ischemic attack, stroke, dementia, mild cognitive impairment, and other brain diseases.${
                        System.getProperty("line.separator")
                    } ${System.getProperty("line.separator")}Raised blood pressure will block arteries and limit blood reaching the brain. The brain could not function when it is short of blood supply, leading to transient ischemic attack (TIA).${
                        System.getProperty(
                            "line.separator"
                        )
                    } ${System.getProperty("line.separator")}Excessive blood pressure leads to brain blood vessels losing their flexibility. This phenomenon makes it impossible for nutrients and oxygen to be transported to the brain, which causes brain cells to die and stroke occurs. The higher your blood pressure is, the greater your chance of developing a stroke. Studies have shown that high blood pressure can boost the risk of stroke in men by 220%.${
                        System.getProperty(
                            "line.separator"
                        )
                    } ${System.getProperty("line.separator")}Except for TIA and stroke, high blood pressure can also result in vascular sclerosis of your cognitive area. That means patients can be affected with cognitive impairment and gradually may develop into dementia."
                )
            )
            listProblem.add(
                Info(
                    "3. Hypertension will cause kidney problems",
                    "Kidney and blood pressure are closely linked. The blood carries metabolic waste through the kidneys. A healthy kidney filters about half a cup of blood every minute, discharging excess water and metabolic waste as urine.${
                        System.getProperty("line.separator")
                    } ${System.getProperty("line.separator")}Therefore, when the blood pressure is high, the walls of blood vessels will thicken to resist the excessive pressure. The price of this change is that the blood vessel becomes narrower, which is not conducive to the filtration of wastes. Furthermore, even the basic blood supply to the kidneys cannot be guaranteed. It leads to ischemic atrophy of the kidneys, chronic kidney disease, and the development of renal failure over time."
                )
            )
            listProblem.add(
                Info(
                    "4. Hypertension affects eyesight",
                    "Extreme high blood pressure will cause swelling of the retina. Patients will experience blurred vision or even loss of eyesight.${
                        System.getProperty("line.separator")
                    } ${System.getProperty("line.separator")}The retina is at the back of the eye and functions image focusing. When the blood pressure is too high, the blood vessel walls of the retina will thicken and make the blood vessels narrow. Over time, high blood pressure can damage retinal blood vessels, limit retinal function, and put pressure on the optic nerve, causing vision problems."
                )
            )
            //
            listUnderstand.add(
                Info(
                    "Understanding Hypotension Symptoms",
                    "<p>Hypotension symptoms include:</p>\n" +
                            "<p>&bull; Blurred vision</p>\n" +
                            "<p>&bull; Dizziness or fainting</p>\n" +
                            "<p>&bull; Inability to concentrate</p>\n" +
                            "<p>&bull; Nausea</p>\n" +
                            "<p>&bull; Rapid breathing</p>\n" +
                            "<p>&bull; Fatigue</p>\n"
                )
            )
            listUnderstand.add(
                Info(
                    "Understanding Hypotension Types",
                    "<<p>If you keep getting these symptoms and wonder the reason, you can get your blood pressure measured and check below to see whether you belong to these types of hypotension.</p>\n" +
                            "<h2>1. Orthostatic hypotension</h2>\n" +
                            "<p>Hypotension caused by rising from a lying down or sitting position to standing is called orthostatic hypotension. It is common as nearly one-fifth of elderly over 65 years old will have it. Besides, beta-blockers, ACE inhibitors, antidepressants and drugs used to treat Parkinson&apos;s disease can also lead to orthostatic hypotension.</p>\n" +
                            "<h2>2. Postprandial hypotension</h2>\n" +
                            "<p>Postprandial hypotension is often considered a geriatric condition, as it usually appears when older adults finish their eating, and almost no young people will have it.</p>\n" +
                            "<p>When blood flows to the intestines after a meal, the heart beats faster, while the blood vessels in other parts of the body would constrict to maintain blood pressure. However, some elderly people&apos;s bodies cannot function normally. When blood flows to the intestines, the heart rate cannot be increased sufficiently, and the vasoconstriction is not enough to maintain blood pressure, which causes a drop in blood pressure.</p>\n" +
                            "<h2>3. Neurally mediated hypotension</h2>\n" +
                            "<p>This type of low blood pressure occurs when the heart and the brain lack communication. People standing for a long time or being very emotional can have neurally mediated hypotension. At that time, the heart needs to pump blood faster to transport blood to the brain. However, the brain delivers the message that the heart rate should be lowered, which brings blood vessels dilatation of lower body and arms, thereby contributing to not enough blood getting to the brain.</p>\n"
                )
            )
            listUnderstand.add(
                Info(
                    "Understand Hypotension: Symptoms & Types",
                    "<p>Understanding Other Hypotension Causes</p>\n" +
                            "<h2>Other causes of consistent hypotension</h2>\n" +
                            "<p>&bull; Pregnancy, hormonal changes affect the blood vessels and circulatory system so that blood pressure may be lower during the first 24 weeks of pregnancy</p>\n" +
                            "<p>&bull; Arrhythmia (abnormal heartbeat)</p>\n" +
                            "<p>&bull; Heart disease</p>\n" +
                            "<p>&bull; Certain over-the-counter drugs used in combination with hypertension drugs</p>\n" +
                            "<p>&bull; Endocrine disorders such as diabetes, adrenal insufficiency and thyroid disease</p>\n" +
                            "<h2>Reasons for sudden fall of blood pressure</h2>\n" +
                            "<p>&bull; Loss of blood from bleeding</p>\n" +
                            "<p>&bull; Low body temperature</p>\n" +
                            "<p>&bull; High body temperature</p>\n" +
                            "<p>&bull; Heart muscle disease causing heart failure</p>\n" +
                            "<p>&bull; Sepsis, a severe blood infection</p>\n" +
                            "<p>&bull; Severe dehydration from vomiting, diarrhea, or fever</p>\n" +
                            "<p>&bull; A reaction to medication or alcohol</p>\n" +
                            "<p>&bull; A severe allergic reaction</p>\n"
                )
            )
            //
            listDrugsHyper.add(
                Info(
                    "",
                    "If you are diagnosed with high blood pressure and need medical treatment, you might wonder what treatment option is useful and suitable to take care of your body. Here we present four medications that your doctor considers first for you. You can check what you are taking to understand the therapy better and to control annoying hypertension better."
                )
            )
            listDrugsHyper.add(
                Info(
                    "1. Angiotensin-converting enzyme (ACE)",
                    "<p>This kind of medication plays an important role in relaxing blood vessels and blocking some hormone&apos;s actions in order to control blood pressure. Meanwhile, what needs to be mentioned is that it would make blood supply to kidneys less efficient.</p>\n" +
                            "<p><br></p>\n" +
                            "<h2>WHO should not take ACE?</h2>\n" +
                            "<p><strong>&bull; Pregnant women</strong></p>\n" +
                            "<p>ACE can be harmful to babies during the last six months of pregnancy. Thus, to better protect yourself and your baby, you&apos;d better choose another type of treatment to control blood pressure under the guidance of your doctor.</p>\n" +
                            "<p><strong>&bull; People with kidney diseases</strong></p>\n" +
                            "<p>As ACE could reduce the kidney blood supply, it is not safe for this type of person to use it.</p>\n" +
                            "<p><strong>&bull; People with a severe allergic reaction</strong></p>\n" +
                            "<p>If taking the ACE causes any severe allergic reaction to your body, stop taking it and seek medical care immediately.</p>\n"
                )
            )
            listDrugsHyper.add(
                Info(
                    "2. Diuretics",
                    "<p>Another common type of hypertension medication is diuretic, which facilitates urination to discharge excess sodium (salt) and water to control blood pressure. It is often applied with other medications in treating high blood pressure.</p>\n" +
                            "<p><br></p>\n" +
                            "<h2>WHO should not take diuretics?</h2>\n" +
                            "<p><strong>&bull; Older people</strong></p>\n" +
                            "<p>They tend to have a more severe reaction to dehydration, which indicates diuretics should be avoided.</p>\n" +
                            "<p><strong>&bull; Pregnant and breastfeeding women</strong></p>\n" +
                            "<p>Diuretics may be passed from the mother&apos;s body to the baby, thereby contributing to the dehydration of the baby.</p>\n" +
                            "<p><strong>&bull; Kids</strong></p>\n" +
                            "<p>Diuretics can cause calcium deficiency, leading to impaired bone development.</p>\n"
                )
            )
            listDrugsHyper.add(
                Info(
                    "3. Angiotensin II receptor blockers (ARBS)",
                    "<p>To illustrate how ARB works to lower your blood pressure, we have to mention angiotensin. It can narrow blood vessels when binding in a receptor place. The ARBS prevent its binding on blood vessels, hence, making vessels staying open and reducing blood pressure.</p>\n" +
                            "<p><br></p>\n" +
                            "<h2>WHO should not take ARBs?</h2>\n" +
                            "<p><strong>&bull; People with certain kidney problems</strong></p>\n" +
                            "<p>Individuals with narrowed arteries to the kidney (renal artery stenosis) or very poor kidney function can have severe reactions to ARBs.</p>\n" +
                            "<p><strong>&bull; People with low levels of sodium in the blood</strong></p>\n" +
                            "<p>ARBS can inhibit the mechanism of angiotensin II, thereby reducing sodium in the renal tubules and worsening your sodium deficiency.</p>\n" +
                            "<p><strong>&bull; Pregnant and breastfeeding women &amp;#38; people with severe allergy</strong></p>\n" +
                            "<p>Those people also need to ask for doctor&apos;s advice concerning taking ARBs.</p>\n"
                )
            )
            listDrugsHyper.add(
                Info(
                    "4. Calcium channel blockers",
                    "<p>You may wonder how blood pressure control can be related to calcium. However, calcium can flow into muscle cells of the heart and arteries, making muscles contracting stronger and harder. Calcium channel blockers work by blocking calcium into your blood vessels, which relaxes muscle contractions to lower your blood pressure.</p>\n" +
                            "<p><br></p>\n" +
                            "<h2>WHO should not take calcium channel blockers?</h2>\n" +
                            "<p><strong>&bull; People with heart problems</strong></p>\n" +
                            "<p>If you have congestive heart failure or structural heart problems, calcium channel blockers may decrease your heart rate and harm your body</p>\n" +
                            "<p><strong>&bull; Pregnant and breastfeeding women &amp;#38; people with severe allergy</strong></p>\n" +
                            "<p>Those people also need to ask for doctor&apos;s advice concerning taking calcium channel blockers.</p>\n"
                )
            )
            //
            listControl.add(
                Info(
                    "",
                    "Fighting against the 'silent killer' is a lifelong commitment. Don't just rely on the medications and do make some changes to save your heart. By following the heart-healthy lifestyle in this article, you may see your numbers back to where they should be. Let's take a step now to reduce your blood pressure and improve daily life."
                )
            )
            listControl.add(
                Info(
                    "1. Keep a healthy weight",
                    "Weight and blood pressure are closely connected with each other. As the weight gains, the blood pressure rises accordingly. Losing excessive weight can expand blood vessels to make the heart pump blood better. Research has proved that losing weight can reduce systolic number by 4.5 to 8.5 mmHg and diastolic number by 3.2 to 6.5 mmHg.${
                        System.getProperty("line.separator")
                    } ${System.getProperty("line.separator")}It does not mean that you are required to make great efforts in losing lots of weight since only losing 5% of your body weight shall make a difference in blood pressure-lowering.${
                        System.getProperty(
                            "line.separator"
                        )
                    } ${System.getProperty("line.separator")}Meanwhile, you need to attach great importance to watching your waistline. Too much bulk around your midsection can affect your blood pressure. It is suggested that men keep waist measurement to less than 40 inches and women be no more than 35 inches.${
                        System.getProperty(
                            "line.separator"
                        )
                    } ${System.getProperty("line.separator")}"
                )
            )
            listControl.add(
                Info(
                    "2. Eat a DASH diet",
                    "<p>The Dietary Approaches to Stop Hypertension (DASH) diet is created to prevent and treat high blood pressure and improve cholesterol. Besides, it also aids weight loss, cancer risk reduction, etc. The regular DASH diet program encourages no more than 1 teaspoon (2,300 mg) of sodium per day. By reducing sodium taken in as well as increasing intake in potassium, magnesium and calcium, the DASH diet can lower your systolic blood pressure by as much as 11mmHg.</p>\n" +
                            "<p><br></p>\n" +
                            "<p>When you buy any food, learn to read labels to check the proportion of sodium. If it contains more than 20% of sodium, stop buying it.</p>\n" +
                            "<p><br></p>\n" +
                            "<p>Generally speaking, following a DASH diet includes:</p>\n" +
                            "<p>&bull; Eating fruits, vegetables, whole grains, and lean meats</p>\n" +
                            "<p>&bull;Taking low-fat dairy products</p>\n" +
                            "<p>&bull; Limiting foods with saturated and trans fats, such as processed foods</p>\n" +
                            "<p>&bull; Cut back sugary foods and drinks</p>\n"
                )
            )
            listControl.add(
                Info(
                    "3. Limit alcohol",
                    "<p>Surprisingly, 16% of hypertension cases all over the world are highly related to alcohol. Drinking more than the moderation level can raise blood pressure by as much as 7 mmHg.</p>\n" +
                            "<p><br></p>\n" +
                            "<p>To protect your heart and better treat hypertension, men should not drink more than two and women need to limit alcohol intake to one drink a day. In order to measure your alcohol intake more intuitively, one drink equals 12 ounces of beer, five ounces of wine or 1.5 ounces of 80-proof liquor.</p>\n" +
                            "<p><br></p>\n" +
                            "<p>Attention! If you are taking hypertensive medications, you&apos;d better stop drinking alcohol as it will decrease the effectiveness.</p>\n"
                )
            )
            listControl.add(
                Info(
                    "4. Exercise regularly",
                    "When you exercise regularly, your heart rates and breathing will increase to make your heart pump stronger, thereby reducing your blood pressure. If you have hypertension, try aerobic workouts, strength training and flexibility exercises with moderate intensity. 150 minutes per week can significantly improve your blood pressure as useful as some medications. You can choose jogging, walking, swimming, dumbbell workouts, yoga, or any exercises you like to make your routine full of fun and healthy." +
                            "" +
                            "It's important to be consistent because if you stop exercising, your blood pressure can rise again."
                )
            )
            listControl.add(
                Info(
                    "5. Manage stress",
                    "<p>Your heart rate increases and blood vessels constrict when you are in a stressful state over time. Meanwhile, when you are under stress, you are more likely to engage in habits like drinking alcohol or taking processed food which can increase your BP number.</p>\n" +
                            "<p><br></p>\n" +
                            "<p>Here are some tips to manage your stress:</p>\n" +
                            "<p>&bull; Look into what you can solve. If a task annoys you, it is essential to make a plan and deal with it as soon as possible.</p>\n" +
                            "<p>Ease yourself. It&apos;s time to show yourself some love and do things you enjoy, whether it&apos;s listening to music, doing yoga or reading a book.</p>\n" +
                            "<p>&bull; Pay attention to staying away from negative habits like drinking alcohol or smoking.</p>\n"
                )
            )
            listControl.add(
                Info(
                    "6. Quit smoking",
                    "<p>Every puff of cigarette smoke leads to an immediate blood pressure increase and a strong response of your heart. If you keep smoking, the nicotine will damage your vessel walls and accelerate fatty materials blocking arteries to cause hypertension. The nicotine can also affect medications effects negatively. Moreover, exposure to secondhand smoke will also cause the same effect.</p>\n" +
                            "<p><br></p>\n" +
                            "<p>For your family members and your heart health, it is high time to quit smoking.</p>\n"
                )
            )
            //
            listLower.add(
                Info(
                    "",
                    "Are you seeking drug-free ways to primary prevention, treatment, and control of hypertension? Then you've come to the right place. This article will introduce physical exercises to help reduce blood pressure. Add them into your daily routine, you can view what a big difference you are going to make."
                )
            )
            listLower.add(
                Info(
                    "1. Aerobic exercise",
                    "<p>Aerobic activity can be an effective way to control high blood pressure. It has been proved that regular aerobic activity can contribute to a noticeable increase in heart rate and breathing, thereby reducing blood vessel stiffness so that blood can flow more easily. On average, regular aerobic exercise lowers resting systolic Blood Pressure 5 to 8 mmHg.</p>\n" +
                            "<p><br></p>\n" +
                            "<p>Please do keep in mind that you can lose gains if stopping the exercise. Moderate-intensity exercise for 5 to 7 days per week is needed. Meanwhile, patients with hypertension are required to conduct a minimum of 30 min or up to 60 min aerobic workout each day. If you take a 30-minute workout as challenging for you, you can split it into 10-minute bouts.</p>\n" +
                            "<p><br></p>\n" +
                            "<h2>EXAMPLES of aerobic activities using large muscle groups</h2>\n" +
                            "<p>&bull; Climbing stairs</p>\n" +
                            "<p>&bull; Walking</p>\n" +
                            "<p>&bull; Jogging</p>\n" +
                            "<p>&bull; Bicycling</p>\n" +
                            "<p>&bull; Swimming&nbsp;</p>\n" +
                            "<p>&bull; Dancing</p>\n"
                )
            )
            listLower.add(
                Info(
                    "2. Resistance training",
                    "<p>Except for aerobic exercises, you can also add resistance training as a supplement to widen blood vessels, in order words, improve blood pressure directly and with no harm. Doing it usually needs weights or equipment such as resistance-training machines. It is recommended to do it twice or three times a week with moderate intensity. Besides, do not practice the same muscle part or do resistance exercises continuously. Altering the body part helps lower your blood pressure.</p>\n" +
                            "<p><br></p>\n" +
                            "<h2>PRECAUTIONS</h2>\n" +
                            "<p>&bull; Seek professional guidance;</p>\n" +
                            "<p>&bull; Less repetitions are the best;</p>\n" +
                            "<p>&bull; Rest for 90 or more seconds between each set;</p>\n" +
                            "<p>&bull; Resistance exercise should occur at a controlled speed but not too slow;</p>\n" +
                            "<p>&bull; Do not hold your breath as it will elevate your blood pressure.</p>\n" +
                            "<p><br></p>\n" +
                            "<h2>EXAMPLES of resistance training with equipment</h2>\n" +
                            "<p>&bull; Chest press</p>\n" +
                            "<p>&bull; Shoulder press</p>\n" +
                            "<p>&bull; Triceps extension</p>\n" +
                            "<p>&bull; Biceps curl</p>\n" +
                            "<p>&bull; Pull-down</p>\n" +
                            "<p>&bull; Lower-back extension</p>\n" +
                            "<p>&bull; Quadriceps extension</p>\n" +
                            "<p>&bull; Calf raise</p>\n"
                )
            )
            listLower.add(
                Info(
                    "3. Flexibility workout",
                    "<p>Simple flexibility workouts or stretches offer benefits for hypertensives. When you stretch your muscles and joints, you are actually stretching blood vessels and arteries and reduce the stiffness of blood flow. It is time to add stretching into your workout routine.</p>\n" +
                            "<p><br></p>\n" +
                            "<p>According to studies, doing it for 2 to 5 days per week can lower blood pressure. Meanwhile, it is essential to hold every exercise for 10 to 30s to ensure the gains.</p>\n" +
                            "<p><br></p>\n" +
                            "<p>EXAMPLES of flexibility</p>\n" +
                            "<p>&bull; Yoga</p>\n" +
                            "<p>&bull; Pilates</p>\n"
                )
            )
            //
            listDiagnose.add(
                Info(
                    "",
                    "<p>When you have chronic hypotension symptoms, you might be curious about how you will be diagnosed and treated in the hospital. Let&apos;s look at what to know about hypotension diagnosis.</p>\n" +
                            "<p><br></p>\n" +
                            "<p>Your doctor may need to perform tests below if he suspects you have hypotension:</p>\n"
                )
            )
            listDiagnose.add(
                Info(
                    "1. Blood test",
                    "The most effective way to grasp your overall blood information is taking blood tests. Your blood sugar level and red blood cell count will be collected to examine whether you have hypotension or not."
                )
            )
            listDiagnose.add(
                Info(
                    "2. Tilt table test",
                    "<p>Another common method to test suspected orthostatic hypotension is the tilt table test. You will rest while supine for few minutes before the test. During testing, the table would be slowly elevated to an upright angle for a while. In the meantime, your heart rate, blood pressure, and symptoms will be measured at regular intervals.</p>\n" +
                            "<p><br></p>\n" +
                            "<p>You will be considered as having orthostatic hypotension if your systolic number drops 20mmHg or the diastolic one falls 10mmHg below the baseline.</p>\n" +
                            "<p><br></p>\n" +
                            "<p>If symptoms occur during testing, the patient should be returned to the supine position immediately.</p>\n"
                )
            )
            listDiagnose.add(
                Info(
                    "3. Electrocardiogram",
                    "<p>To check potential heart problems that can lead to blood pressure drops, an electrocardiogram cannot be missed. It is a painless and noninvasive test, during which soft and sticky patches (electrodes) are attached to the skin of your chest, arms, and legs to attain the heart's electrical signals.</p>\n\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p>Any irregularities in your heart rhythm, structural problems in your heart, problems with the supply of blood and oxygen to your heart muscle or other heart issues would be detected accurately via the test.</p>\n"
                )
            )
            //
            listDrugsHypo.add(
                Info(
                    "",
                    "If your tests taken show a positive outcome and you have symptoms, you may need medical therapies to cure hypotension. So what are the treatments for low blood pressure?"
                )
            )
            listDrugsHypo.add(
                Info(
                    "1. Fludrocortisone",
                    "<p>Fludrocortisone is a medication that seems to help most types of low blood pressure. It increases the blood pressure by raising the salt level in the body which in turn increases the volume of the blood.</p>\n" +
                            "<p><br></p>\n" +
                            "<h2>Side effects include:</h2>\n" +
                            "<p>&bull; Headache</p>\n" +
                            "<p>&bull; Nausea</p>\n" +
                            "<p>&bull; Dizziness</p>\n" +
                            "<p>&bull; Insomnia</p>\n" +
                            "<p>&bull; Supine hypertension</p>\n" +
                            "<p>&bull; Congestive heart failure</p>\n" +
                            "<p>&bull; Increased risk of infection</p>\n" +
                            "<p><br></p>\n" +
                            "<h2>WHO should not take it?</h2>\n" +
                            "<p>&bull; People with lung, liver, kidney, and heart problems should consult their doctors before taking it.</p>\n" +
                            "<p>&bull; People with cancer, hypertension, diabetes or any infection risk should take caution.</p>\n" +
                            "<p>&bull; Besides, pregnant and breastfeeding women as well as people with a severe allergy need to ask for a doctor&apos;s advice concerning taking it.</p>\n"
                )
            )
            listDrugsHypo.add(
                Info(
                    "2. Pyridostigmine",
                    "<p>Pyridostigmine would improve nerve cell transmission for orthostatic hypotension patients and trigger the reflex that controls blood pressure, especially helpful for patients with high supine blood pressure. Meanwhile, its side effects are minor and mild.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<h2>Side effects are:</h2>\n" +
                            "<p>&bull; Abs cramps</p>\n" +
                            "<p>&bull; Increased urination</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<h2>WHO should not take it?</h2>\n" +
                            "<p>&bull; People with a blockage in the intestine or urinary tract.</p>\n" +
                            "<p>&bull; Besides, pregnant and breastfeeding women as well as people with a severe allergy need to ask for a doctor's advice concerning taking it.</p>\n"
                )
            )
            listDrugsHypo.add(
                Info(
                    "3. Midodrine",
                    "<p>It increases your blood pressure by activating receptors of the blood vessels to narrow them. Midodrine can boost blood pressure even when you are at rest. Thus, this medicine should be used only if you have severely low blood pressure that affects your daily life or other therapies do not work.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<h2>Side effects cover:</h2>\n" +
                            "<p>&bull; Chills</p>\n" +
                            "<p>&bull; Numbness</p>\n" +
                            "<p>&bull; Headache</p>\n" +
                            "<p>&bull; Dizziness</p>\n" +
                            "<p>&bull; Nausea</p>\n" +
                            "<p>&bull; Fatigue</p>\n" +
                            "<p>&bull; Difficulty in urination</p>\n" +
                            "<p>&bull; Increased urination</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<h2>WHO should not take it?</h2>\n" +
                            "<p>&bull; Midodrine can cause severe reactions when combining with certain medications. Hence, do tell your doctor what medications, vitamins, nutritional supplements, and herbal products you are taking or what you used to take before having it.</p>\n" +
                            "<p>&bull; People with diabetes, vision problems, or liver disease cannot use midodrine to treat hypotension.</p>\n" +
                            "<p>People who are going to take any kind of surgery should consult their doctor.</p>\n" +
                            "<p>&bull; Besides, pregnant and breastfeeding women as well as people with a severe allergy need to ask for a doctor's advice concerning taking it.</p>\n"
                )
            )
            //
            listTipsHyper.add(
                Info(
                    "",
                    "There may be cases where blood pressure shoots up unexpectedly (systolic blood pressure reaching 180 and/or diastolic one reaching 120 mmHg) and emergency care is required. After calling for immediate medical service or during your waiting for the ambulance, what can be the first aid? No worries, we have prepared all the know-how for you."
                )
            )
            listTipsHyper.add(
                Info(
                    "1. Calm yourself down benefits blood pressure lowering",
                    "<p>One thing all hypertension patients need to keep in mind is that lower stress means lower blood pressure. Researchers have found that relaxing fully for a few minutes can decrease systolic blood pressure by 10 mmHg or more. Thus, it is the most natural way to lower your blood pressure during hypertensive urgency. Below are some relaxation tips you can try:</p>\n" +
                            "<p>&bull; Stop your current task</p>\n" +
                            "<p>&bull; Take deep breaths</p>\n" +
                            "<p>&bull; Sit down or lie flat</p>\n" +
                            "<p>&bull; Listen to relaxing sounds</p>\n" +
                            "<p>&bull; Meditate</p>\n"
                )
            )
            listTipsHyper.add(
                Info(
                    "2. Take your blood pressure medication",
                    "If you are a hypertension patient, you must have blood pressure medicines that the doctor has prescribed for you. Medication is the main option for treating high blood pressure. Therefore, if you or your family members experience a hypertensive emergency, never forget to take medical treatment as soon as possible."
                )
            )
            listTipsHyper.add(
                Info(
                    "3. Sip a cup of hibiscus tea",
                    "<p>Don't be surprised. It has been improved by many studies that hibiscus tea can have both direct and indirect positive influences on your blood pressure. As useful as certain medical treatments, anthocyanins and some antioxidants in the hibiscus can lower your blood pressure. Drinking hibiscus tea can lower your systolic blood pressure number by 7 points at best.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p>Meanwhile, sipping tea can also reduce your blood pressure indirectly by lower stress. The soothing tea helps you feel calmer, control anger, and lower your BP numbers.</p>\n"
                )
            )
            listTipsHyper.add(
                Info(
                    "4. Have dark chocolate",
                    "<p>Eating a small amount of dark chocolate can help lower your blood pressure. It contains catechins and procyanidins, which can lead to dilation of the blood vessels. According to research, both systolic and diastolic numbers were lowered by 2 to 3 mmHg after taking dark chocolate. Although changes are modest, the positive effect is true.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p>Moreover, dark chocolate can indirectly affect your hypertension positively by helping the release of endorphins that will calm you down. It's time to stock some just in case.</p>\n"
                )
            )
            //
            listTipsHypo.add(
                Info(
                    "",
                    "When your blood pressure is very low or drops rapidly, it can be a medical emergency. The first step should be contacting your doctor for immediate medical treatment and taking a careful review of your medications. But what else can you do? Learn tips below and you will no longer be clueless."
                )
            )
            listTipsHypo.add(
                Info(
                    "1. Lie down",
                    "When you are experiencing any hypotensive symptoms, try to sit or lie down on a flat surface safely and immediately. Keep standing can make your postural hypotension worse. Lying down or sitting benefits your blood pressure normalization."
                )
            )
            listTipsHypo.add(
                Info(
                    "2. Stay hydrated",
                    "One of the common causes of hypotension is dehydration. Thus, drink more fluids, covering water, coconut water, and sports drink, can not only ease your dehydration but also helps maintain the fluids in your body. Besides, you can add a small amount of salt or sugar to improve your blood pressure or restore your glucose level."
                )
            )
            listTipsHypo.add(
                Info(
                    "3. Take adequate salt",
                    "<p>Try eating more salty foods or lick a pinch of salt to let the sodium raise your blood pressure. You can also drink sports drinks or take oral rehydration salts (ORS) which can rehydrate you and provide salt as well as other electrolytes to improve your blood pressure.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p>However, don't take ORS if you are a diabetes patient and do make sure you do not take too much salt. It may lead to problems like water retention along with high blood pressure.</p>\n"
                )
            )
            listTipsHypo.add(
                Info(
                    "4. Wear compression stockings",
                    "<p><br />People with orthostatic hypotension usually have blood pools in the legs. Compression stockings work by applying slight pressure to your legs to lead blood flow to your heart, which indicates the thigh-high or waist-high version will be effective.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p>Please note that certain patients are not suitable to wear them. Thus, consulting your doctor is vital.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p>Now you know what to do when you or someone else experiences hypotension. However, don't try to take yourself as an expert. Remember to always visit your doctor and never self-medicate.</p>\n"
                )
            )
        }
    }
}