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
                    "Phạm vi nào có thể được xác định là tăng huyết áp? Nếu một trong các chỉ số của bạn đạt tiêu chuẩn về huyết áp cao, còn chỉ số kia thì không, làm thế nào để xác định điều đó? Để xác định chỉ số huyết áp của mình, bạn có thể xem loại huyết áp bên dưới."                )
            )
            listKnow.add(Info("header", ""))
            listKnow.add(
                Info(
                    "1. Huyết áp thấp",
                    "Nếu chỉ số của bạn dưới 90/60 mmHg, bạn có thể bị hạ huyết áp. Bạn không cần bất kỳ điều trị nào vì huyết áp thấp không gây hại và thường không có triệu chứng. Tuy nhiên, khi huyết áp thường xuyên tụt đột ngột trên 20 mmHg, tụt đột ngột do dùng một số loại thuốc, hoặc cảm thấy chóng mặt, ngất xỉu, mệt mỏi... thì nên tìm cách điều trị."
                )
            )
            listKnow.add(
                Info(
                    "2. Phạm vi bình thường",
                    "Nếu chỉ số của bạn cao hơn 90/60 mmHg và dưới 120/80 mmHg thì huyết áp của bạn ở mức bình thường. Bạn cần duy trì hoặc áp dụng lối sống lành mạnh để ngăn ngừa bệnh tăng huyết áp phát triển.${                        System.getProperty(
                        "line.separator"
                    )
                    } ${System.getProperty("line.separator")}Ngoài ra, nếu bạn có bất kỳ thành viên nào trong gia đình bị tăng huyết áp, bạn nên chú ý hơn đến lối sống của mình vì nguy cơ mắc bệnh tăng huyết áp có thể cao."
                )
            )
            listKnow.add(
                Info(
                    "3. Cao",
                    "Khi huyết áp của bạn cao hơn bình thường nhưng không đạt 130/80 mmHg, huyết áp của bạn được coi là tăng cao. Nếu lối sống và thói quen ăn uống không được cải thiện, bạn rất có khả năng bị cao huyết áp."                )
            )
            listKnow.add(
                Info(
                    "4. Giai đoạn huyết áp cao 1",
                    "Chỉ số huyết áp tâm thu ổn định trong khoảng từ 130 đến 139 mmHg hoặc chỉ số huyết áp tâm trương trong khoảng từ 80 đến 89 mmHg có nghĩa là bạn đang ở giai đoạn tăng huyết áp 1.${                        System.getProperty(
                        "line.separator"
                    )
                    } ${System.getProperty("line.separator")}Nói chung, bạn chỉ cần cải thiện lối sống để kiểm soát huyết áp. Tuy nhiên, nếu bạn có nguy cơ cao mắc bệnh tim mạch (bệnh tim, đột quỵ, v.v.) thì bạn cần bắt đầu dùng thuốc phù hợp."
                )
            )
            listKnow.add(
                Info(
                    "5. Giai đoạn huyết áp cao 2",
                    "Tăng huyết áp giai đoạn 2 là khi bạn có chỉ số huyết áp tâm thu ổn định trong khoảng từ 140 đến 180 mmHg hoặc huyết áp tâm trương trong khoảng từ 90 đến 120 mmHg.${                        System.getProperty(
                        "line.separator"
                    )
                    } ${System.getProperty("line.separator")}Ở giai đoạn này, bạn nên kết hợp thay đổi lối sống và một hoặc nhiều loại thuốc. Sau tháng điều trị đầu tiên và cải thiện lối sống, nếu huyết áp của bạn được kiểm soát, bạn có thể đến bệnh viện để đo lại sau 3 đến 6 tháng. Nếu huyết áp tăng cao hoặc không thay đổi thì cần tham khảo ý kiến bác sĩ để chuyển sang phương pháp điều trị khác."
                )
            )
            listKnow.add(
                Info(
                    "6. Huyết áp cao trầm trọng",
                    "Nếu bạn đang đo huyết áp tại nhà và thấy chỉ số tâm thu trên 180 mmHg hoặc chỉ số tâm trương trên 120 mmHg, trước tiên bạn cần bình tĩnh sau đó đợi vài phút để kiểm tra lại. Nếu bạn vẫn ở trong phạm vi cho phép. cơn tăng huyết áp, đừng ngần ngại gọi cấp cứu hoặc đến bệnh viện ngay bây giờ."                )
            )
            //
            listLearn.add(
                Info(
                    "",
                    "Dù đo huyết áp tại nhà hay ở bệnh viện, chắc hẳn bạn cũng đang băn khoăn không biết huyết áp có ý nghĩa gì hay bởi hai cột số trên màn hình máy. Đừng lo lắng, sau khi đọc bài viết này, tất cả sẽ được giải đáp. "                )
            )
            listLearn.add(
                Info(
                    "Huyết áp là gì ?",
                    "Trái tim giống như một cái máy bơm nước, không ngừng bơm máu đến các mạch máu khắp cơ thể. Khi máu chạy, nó sẽ đẩy vào thành mạch máu. Lực đẩy chính là huyết áp."                )
            )
            listLearn.add(
                Info(
                    "Hai số trên thiết bị BP là gì?",
                    "Hai chỉ số trên máy đo huyết áp của bạn biểu thị huyết áp tâm thu và huyết áp tâm trương. Số trên cùng hoặc số đầu tiên là huyết áp tâm thu, nghĩa là lượng áp lực trong động mạch khi tim bạn đẩy máu qua động mạch. Số dưới cùng hoặc số thứ hai là tâm trương, đề cập đến áp lực trong động mạch khi tim bạn ở giữa các nhịp đập."                )
            )
            //
            listFind.add(
                Info(
                    "Tăng huyết áp",
                    "Hệ thống tim mạch của con người giống như một hệ thống tuần hoàn nước, trong đó máu lưu thông liên tục như nước và vận chuyển oxy cũng như dinh dưỡng đến các cơ quan và mạch máu.${                        System.getProperty("line.separator")
                    } ${
                        System.getProperty("line.separator")
                    }Việc lưu thông máu trở nên khó khăn khi các mạch máu mất đi tính đàn hồi. Khi đó, cơ thể cố gắng tăng cường sức rặn khiến máu lưu thông dễ gây tăng huyết áp."
                ))
            listFind.add(
                Info(
                    "│Huyết áp thấp",
                    "Nếu huyết áp của bạn liên tục giảm 90/60 mmHg hoặc giảm đột ngột trên 20 mmHg, bạn nên theo dõi huyết áp của mình. Hạ huyết áp không có triệu chứng thường không cần điều trị. Tuy nhiên, cần chẩn đoán chính xác và phân loại liệu pháp điều trị nếu có triệu chứng hoặc thất bại đột ngột xuất hiện."                )
            )
            //
            listBreak.add(
                Info(
                    "",
                    "Bạn có thể nghe thấy đủ loại lầm tưởng về huyết áp. Việc tin vào những lầm tưởng này thực sự có thể khiến việc kiểm soát huyết áp của bạn trở nên vô ích hoặc thậm chí khiến tình trạng của bạn trở nên tồi tệ hơn. Dưới đây là 7 hiểu lầm phổ biến liên quan đến huyết áp. Hãy xem những điều sau đây và ngừng tin vào chúng."                )
            )
            listBreak.add(
                Info(
                    "1.Đừng lo lắng nếu tôi bị huyết áp thấp",
                    "Nói chung, nếu bạn bị hạ huyết áp, một số thay đổi nhỏ trong lối sống có thể giải quyết được vấn đề. Tuy nhiên, nếu bạn bị giảm đột ngột trên 20 mmHg hoặc cảm thấy ngất xỉu, mệt mỏi, mờ mắt, v.v., bạn cần được điều trị chuyên nghiệp. Nếu không được điều trị ngay lập tức, tình trạng hạ huyết áp có thể nguy hiểm đến tính mạng."                )
            )
            listBreak.add(
                Info(
                    "2. Tôi không bị tăng huyết áp nếu không có triệu chứng gì",                    "Do you know that hypertension is called the silent killer? It often runs silent and may have no symptoms, or the symptoms may be mild, which will not be taken seriously while it is causing great damage to your health.${
                        System.getProperty("line.separator")
                    } ${
                        System.getProperty("line.separator")
                    }Khi các triệu chứng xuất hiện, điều đó cho thấy tim, não, thận, mạch máu hoặc các cơ quan khác đã bị tổn thương nghiêm trọng và không thể duy trì các chức năng bình thường. Khi đó, cơ hội điều trị tối ưu đã bị bỏ lỡ. Trong khi đó, các biến chứng ngày càng gia tăng và tính mạng đang bị đe dọa."                )
            )
            listBreak.add(
                Info(
                    "3. Cao huyết áp có thể chữa khỏi",
                    "Chỉ có tăng huyết áp thứ phát mới có thể chữa khỏi vì có nguyên nhân rõ ràng. Khi khỏi bệnh thứ phát, tăng huyết áp thứ phát sẽ không kéo dài nữa.${                        System.getProperty("line.separator")
                    } ${
                        System.getProperty("line.separator")
                    }Bệnh tăng huyết áp nguyên phát vẫn chưa thể chữa khỏi nhưng có thể ngăn ngừa hoặc giảm bớt tác hại của nó đối với cơ thể bằng cách kiểm soát huyết áp về mức bình thường thông qua thuốc hoặc cải thiện lối sống. Tuyên bố rằng huyết áp cao có thể được chữa khỏi bằng các phương pháp đơn giản và không bao giờ tái phát là sai lầm."                )
            )
            listBreak.add(
                Info(
                    "4. Uống rượu vang đỏ giúp kiểm soát huyết áp",
                    "Có truyền thuyết cho rằng resveratrol trong rượu vang đỏ có thể duy trì sức khỏe tim mạch. Tuy nhiên, không có nghiên cứu nào có thể chứng minh tuyên bố đó. Nếu resveratrol thực sự có thể bảo vệ sức khỏe tim mạch của bạn, bạn cần uống quá nhiều rượu vang đỏ để đạt được hiệu quả. có thể gây hại nặng nề cho cơ thể. Ngoài ra, rượu đã được chứng minh là không có lợi cho hệ tim mạch."                )
            )
            listBreak.add(
                Info(
                    "5. Tôi có thể ngừng dùng thuốc nếu huyết áp của tôi được kiểm soát",                    "This is totally wrong and can be misleading. Primary hypertension can never be cured and most patients need to take medication all their lives. Your blood pressure under control is the result of drug control, not that high blood pressure has been cured. Once the drug is stopped, blood pressure is likely to rebound. Moreover, quit taking certain drugs will cause serious withdrawal reactions.${
                        System.getProperty(
                            "line.separator"
                        )
                    } ${System.getProperty("line.separator")}Vì vậy, ngay cả khi huyết áp của bạn đã ổn định trong thời gian dài, bạn cũng không nên thay đổi hoặc dừng thuốc mà không có hướng dẫn thích hợp. Hãy tham khảo ý kiến bác sĩ trước khi thực hiện bất kỳ thay đổi nào đối với thuốc, bạn nên giảm lượng thuốc theo hướng dẫn của bác sĩ, theo dõi huyết áp thường xuyên và duy trì lối sống lành mạnh."
                )
            )
            listBreak.add(
                Info(
                    "6. Người trẻ sẽ không bị cao huyết áp",
                    "Mặc dù bệnh nhân tăng huyết áp thường gặp ở người trung niên và người cao tuổi, nhưng những người trẻ tuổi cũng có thể mắc bệnh này. Mức độ huyết áp bị ảnh hưởng bởi tuổi tác, kích thước cơ thể, mức độ phát triển giới tính, lối sống, v.v."                )
            )
            listBreak.add(
                Info(
                    "7. Chỉ có nam giới mới bị tăng huyết áp",
                    "Sự khác biệt về nguy cơ giữa nam và nữ thực ra phụ thuộc vào độ tuổi. Trước 45 tuổi, nam giới có nguy cơ mắc bệnh tăng huyết áp cao hơn nữ giới. Trong độ tuổi từ 45 đến 64, họ có nguy cơ gần như ngang nhau. Điều đáng chú ý là sau đó 64 tuổi, nguy cơ cao huyết áp của phụ nữ cao hơn nam giới."                )
            )
            //
            listType.add(
                Info(
                    "",
                    "Bạn có biết có hai loại bệnh cao huyết áp chính không? Một loại là nguyên phát và một loại là cao huyết áp thứ phát. Chúng có những nguyên nhân khác nhau và hãy đọc tiếp để biết thêm về chúng."                )
            )
            listType.add(
                Info(
                    "Tăng huyết áp nguyên phát",
                    "Bệnh tăng huyết áp phổ biến nhất là loại nguyên phát. Nó có nghĩa là huyết áp cao liên tục không liên quan đến tình trạng bệnh lý khác. Mặc dù đã có nhiều năm nghiên cứu về bệnh tăng huyết áp nhưng nguyên nhân cụ thể vẫn chưa được xác định. Chế độ ăn uống không lành mạnh, không hoạt động thể chất, tiêu thụ thuốc lá và rượu , thừa cân hoặc béo phì, v.v. có thể làm tăng huyết áp của bạn.${                        System.getProperty("line.separator")
                    } ${System.getProperty("line.separator")}Huyết áp của bệnh nhân sẽ tăng theo độ tuổi. Phương pháp chữa bệnh cho đến nay vẫn chưa được tìm ra. Vì vậy, hầu hết bệnh nhân cần dùng thuốc để kiểm soát huyết áp suốt đời."
                )
            )
            listType.add(
                Info(
                    "Tăng huyết áp thứ phát",
                    "Chỉ có khoảng 5 đến 10% bệnh nhân tăng huyết áp là loại thứ phát. Ngoài ra, người ta đã chứng minh rằng những người trẻ tuổi có nguy cơ mắc bệnh tăng huyết áp thứ phát.${                        System.getProperty(
                        "line.separator"
                    )
                    } ${System.getProperty("line.separator")}Tăng huyết áp thứ phát là do một số loại thuốc như thuốc tránh thai, v.v. hoặc do các tình trạng khác nhau như ngưng thở khi ngủ do tắc nghẽn, bệnh thận, u tuyến thượng thận, các vấn đề về tuyến giáp, dị tật mạch máu, v.v. Khi bệnh nhân ngừng dùng thuốc hoặc chữa khỏi bệnh thứ phát tình trạng tăng huyết áp thứ phát sẽ thuyên giảm."
                )
            )
            //
            listNotice.add(
                Info(
                    "1. Đau đầu",
                    "Đau đầu do huyết áp cao thường là dấu hiệu cấp cứu. Khi huyết áp quá cao, nó có thể làm tổn thương các mạch máu trong não, dẫn đến tăng áp lực nội sọ. Bạn sẽ bị đau đầu từng cơn ở hai bên đầu và bất kỳ hoạt động nào cũng sẽ khiến cơn đau đầu trở nên tồi tệ hơn."                )
            )
            listNotice.add(
                Info(
                    "2. Chóng mặt",
                    "Mặc dù chóng mặt có thể là tác dụng phụ của việc dùng thuốc hạ huyết áp, nhưng bạn không nên coi thường nó. Huyết áp cao nghiêm trọng là nguyên nhân chính gây đột quỵ. Chóng mặt đột ngột, mất thăng bằng hoặc đi lại không vững đều có thể là dấu hiệu của đột quỵ. "                )
            )
            listNotice.add(
                Info(
                    "3. Buồn nôn",
                    "Nếu bạn là một người bị tăng huyết áp và đột nhiên cảm thấy buồn nôn và chán ăn, bạn có thể gặp phải cơn tăng huyết áp. Buồn nôn do huyết áp quá cao thường xảy ra cùng với chóng mặt."                )
            )
            listNotice.add(
                Info(
                    "4. Hụt hơi",
                    "Huyết áp cao sẽ ảnh hưởng đến chức năng tim và phổi, dẫn đến khó thở. Khi bệnh nhân tập thể dục, điều đó có thể dễ nhận thấy hơn."                )
            )
            listNotice.add(
                Info(
                    "5. Đau ngực",
                    "Tăng huyết áp là yếu tố nguy cơ hàng đầu gây ra đau tim và suy tim. Huyết áp cao làm cứng động mạch, khiến lưu lượng máu và cung cấp oxy khó khăn. Điều đó có nghĩa là ít máu đến tim hơn và cuối cùng gây ra đau ngực, còn gọi là đau thắt ngực."                )
            )
            listNotice.add(
                Info(
                    "6.Mờ mắt",
                    "Trên nhãn cầu của bạn có rất nhiều mạch máu nhỏ. Khi huyết áp tăng đến mức nguy hiểm, những mạch máu này có thể bị tổn thương dẫn đến chảy máu, gây mờ mắt, thậm chí mất thị lực."                )
            )
            listNotice.add(
                Info(
                    "7. Chảy máu cam",
                    "Tăng huyết áp thường không gây chảy máu cam trừ khi huyết áp của bạn quá cao. Chảy máu có nghĩa là các mạch bên trong mũi của bạn bị tổn thương. Lúc này, bạn cần gọi xe cấp cứu hoặc đến bệnh viện ngay lập tức."                )
            )
            //
            listProblem.add(
                Info(
                    "",
                    "Huyết áp cao là mối đe dọa lớn đối với sức khỏe của bạn. Nó có thể gây tổn thương dần dần cho tim, não, thận và mắt của bạn mà không có triệu chứng. Nếu bạn muốn biết tác động của bệnh tăng huyết áp lên cơ thể mình, hãy đọc phần bên dưới để có cái nhìn rõ ràng."                )
            )
            listProblem.add(
                Info(
                    "1. Tăng huyết áp dẫn đến bệnh tim",
                    "Huyết áp cao có thể gây ra bệnh tim mạch vành, suy tim, v.v. Một số nghiên cứu minh họa rằng khoảng 1/4 số trường hợp suy tim là do huyết áp cao.${                        System.getProperty("line.separator")
                    } ${System.getProperty("line.separator")}Tăng huyết áp sẽ làm cho động mạch kém linh hoạt và khiến các chất béo làm tắc nghẽn mạch máu, điều này cho thấy việc lưu thông máu đến tim trở nên bất khả thi. Khi đó, tim cần làm cho tâm thất trái hoạt động mạnh hơn và bơm máu đến các cơ quan khác nhau.${  System.getProperty(
                        "line.separator"
                    )
                    } ${System.getProperty("line.separator")}Cuối cùng, khối lượng công việc tăng thêm sẽ khiến tâm thất trái giãn nở, làm tăng đáng kể nguy cơ phát triển bệnh tim. Mặt khác, tim trở nên yếu và không thể bơm máu dẫn đến suy tim."
                )
            )
            listProblem.add(
                Info(
                    "2. Tăng huyết áp có thể làm hỏng não của bạn",
                    "Huyết áp cao có thể gây ra cơn thiếu máu cục bộ thoáng qua, đột quỵ, mất trí nhớ, suy giảm nhận thức nhẹ và các bệnh về não khác.${                        System.getProperty("line.separator")
                    } ${System.getProperty("line.separator")}Huyết áp tăng sẽ làm tắc nghẽn động mạch và hạn chế máu lên não. Não không thể hoạt động khi thiếu máu cung cấp, dẫn đến cơn thiếu máu cục bộ thoáng qua (TIA).${      System.getProperty(
                        "line.separator"
                    )
                    } ${System.getProperty("line.separator")}Huyết áp quá cao khiến mạch máu não mất đi tính linh hoạt. Hiện tượng này khiến chất dinh dưỡng và oxy không thể vận chuyển lên não khiến tế bào não chết và xảy ra đột quỵ. Huyết áp càng cao thì nguy cơ bị đột quỵ càng cao. Các nghiên cứu đã chỉ ra rằng huyết áp cao có thể làm tăng nguy cơ đột quỵ ở nam giới lên 220%.${
                        System.getProperty(
                            "line.separator"
                        )
                    } ${System.getProperty("line.separator")}Ngoại trừ TIA và đột quỵ, huyết áp cao cũng có thể dẫn đến xơ cứng mạch máu ở vùng nhận thức của bạn. Điều đó có nghĩa là bệnh nhân có thể bị suy giảm nhận thức và dần dần có thể phát triển thành chứng mất trí nhớ."
                )
            )
            listProblem.add(
                Info(
                    "3. Tăng huyết áp sẽ gây ra vấn đề về thận",
                    "Thận và huyết áp có mối liên hệ chặt chẽ với nhau. Máu mang chất thải trao đổi chất qua thận. Một quả thận khỏe mạnh lọc khoảng nửa cốc máu mỗi phút, thải lượng nước dư thừa và chất thải trao đổi chất dưới dạng nước tiểu.${                        System.getProperty("line.separator")
                    } ${System.getProperty("line.separator")}Vì vậy, khi huyết áp cao, thành mạch máu sẽ dày lên để chống lại áp lực quá mức. Cái giá của sự thay đổi này là mạch máu trở nên hẹp hơn, không có lợi cho quá trình lọc chất thải. Hơn nữa, ngay cả việc cung cấp máu cơ bản cho thận cũng không thể được đảm bảo. Nó dẫn đến teo thận do thiếu máu cục bộ, bệnh thận mãn tính và phát triển bệnh suy thận theo thời gian."
                )
            )
            listProblem.add(
                Info(
                    "4. Tăng huyết áp ảnh hưởng đến thị lực",
                    "Huyết áp cao sẽ gây sưng võng mạc. Bệnh nhân sẽ bị mờ mắt hoặc thậm chí mất thị lực.${                        System.getProperty("line.separator")
                    } ${System.getProperty("line.separator")}Võng mạc nằm ở phía sau mắt và có chức năng lấy nét hình ảnh. Khi huyết áp quá cao, thành mạch máu của võng mạc sẽ dày lên và khiến mạch máu bị thu hẹp. Theo thời gian, huyết áp cao có thể làm tổn thương mạch máu võng mạc, hạn chế chức năng của võng mạc và gây áp lực lên dây thần kinh thị giác, gây ra các vấn đề về thị lực."
                )
            )
            //
            listUnderstand.add(
                Info(
                    "Tìm hiểu các triệu chứng hạ huyết áp",
                    "<p>Các triệu chứng hạ huyết áp bao gồm:</p>\n" +
                            "<p>&bull; Mờ mắt</p>\n" +
                            "<p>&bull; Chóng mặt hoặc ngất xỉu</p>\n" +
                            "<p>&bull; Không có khả năng tập trung</p>\n" +
                            "<p>&bull; Buồn nôn</p>\n" +
                            "<p>&bull; Thở nhanh</p>\n" +
                            "<p>&bull; Mệt mỏi</p>\n"
                )
            )
            listUnderstand.add(
                Info(
                    "Tìm hiểu các loại hạ huyết áp",
                    "<<p>Nếu bạn tiếp tục gặp những triệu chứng này và thắc mắc nguyên nhân, bạn có thể đo huyết áp và kiểm tra bên dưới để xem bạn có thuộc loại hạ huyết áp này hay không.</p>\n" +
                            "<h2>1. Hạ huyết áp thế đứng</h2>\n" +
                            "<p>Hạ huyết áp do chuyển từ tư thế nằm hoặc ngồi sang tư thế đứng gọi là hạ huyết áp thế đứng. Bệnh này thường gặp vì gần 1/5 người già trên 65 tuổi mắc phải. Ngoài ra, còn dùng thuốc chẹn beta, thuốc ức chế ACE, thuốc chống trầm cảm. và các loại thuốc dùng để điều trị bệnh Parkinson cũng có thể dẫn đến hạ huyết áp thế đứng.</p>\n" +
                            "<h2>2. Hạ huyết áp sau bữa ăn</h2>\n" +
                            "<p>Hạ huyết áp sau bữa ăn thường được coi là một tình trạng lão khoa, vì nó thường xuất hiện khi người lớn tuổi ăn xong và hầu như không có người trẻ nào mắc bệnh này.</p>\n" +
                            "<p>Khi máu chảy đến ruột sau bữa ăn, tim đập nhanh hơn, trong khi các mạch máu ở các bộ phận khác trong cơ thể sẽ co lại để duy trì huyết áp. Tuy nhiên, cơ thể của một số người cao tuổi không thể hoạt động bình thường. Khi máu chảy xuống ruột, nhịp tim không thể tăng đủ, lực co mạch không đủ để duy trì huyết áp nên gây tụt huyết áp.</p>\n" +
                            "<h2>3. Hạ huyết áp qua trung gian thần kinh</h2>\n" +
                            "<p>Loại huyết áp thấp này xảy ra khi tim và não thiếu sự giao tiếp. Những người đứng lâu hoặc xúc động nhiều có thể bị hạ huyết áp qua trung gian thần kinh. Khi đó, tim cần bơm máu nhanh hơn để vận chuyển máu Tuy nhiên, não đưa ra thông điệp rằng nhịp tim nên giảm xuống, khiến các mạch máu ở phần dưới cơ thể và cánh tay bị giãn ra, từ đó góp phần khiến máu lên não không đủ.</p>\n"
                )
            )
            listUnderstand.add(
                Info(
                    "Hiểu về hạ huyết áp: Triệu chứng & phân loại",
                    "<p>Tìm hiểu các nguyên nhân gây hạ huyết áp khác</p>\n" +
                            "<h2>Các nguyên nhân khác gây hạ huyết áp liên tục</h2>\n" +
                            "<p>&bull; Mang thai, nội tiết tố thay đổi ảnh hưởng đến mạch máu và hệ tuần hoàn nên huyết áp có thể thấp hơn trong 24 tuần đầu của thai kỳ</p>\n" +
                            "<p>&bull; Chứng loạn nhịp tim (nhịp tim bất thường)</p>\n" +
                            "<p>&bull; Bệnh tim</p>\n" +
                            "<p>&bull; Một số loại thuốc không kê đơn được sử dụng kết hợp với thuốc tăng huyết áp</p>\n" +
                            "<p>&bull; Rối loạn nội tiết như tiểu đường, suy tuyến thượng thận và bệnh tuyến giáp</p>\n" +
                            "<h2>Nguyên nhân tụt huyết áp đột ngột</h2>\n" +
                            "<p>&bull; Mất máu do chảy máu</p>\n" +
                            "<p>&bull; Nhiệt độ cơ thể thấp</p>\n" +
                            "<p>&bull; Nhiệt độ cơ thể cao</p>\n" +
                            "<p>&bull;Bệnh cơ tim gây suy tim</p>\n" +
                            "<p>&bull; Nhiễm trùng huyết, nhiễm trùng máu nghiêm trọng</p>\n" +
                            "<p>&bull; Mất nước nghiêm trọng do nôn mửa, tiêu chảy hoặc sốt</p>\n" +
                            "<p>&bull; Phản ứng với thuốc hoặc rượu</p>\n" +
                            "<p>&bull; Phản ứng dị ứng nghiêm trọng</p>\n"
                )
            )
            //
            listDrugsHyper.add(
                Info(
                    "",
                    "Nếu bạn được chẩn đoán mắc bệnh cao huyết áp và cần điều trị y tế, bạn có thể băn khoăn không biết lựa chọn điều trị nào hữu ích và phù hợp để chăm sóc cơ thể mình. Sau đây, chúng tôi trình bày bốn loại thuốc mà bác sĩ cân nhắc đầu tiên cho bạn. Bạn có thể kiểm tra xem mình đang mắc bệnh gì. để hiểu rõ hơn về liệu pháp điều trị và kiểm soát tình trạng tăng huyết áp khó chịu tốt hơn."
                )
            )
            listDrugsHyper.add(
                Info(
                    "1. Enzym chuyển đổi angiotensin (ACE)",
                    "<p>Loại thuốc này có vai trò quan trọng trong việc làm giãn mạch máu và ngăn chặn hoạt động của một số hormone nhằm kiểm soát huyết áp. Trong khi đó, điều cần phải đề cập là sẽ khiến việc cung cấp máu cho thận kém hiệu quả hơn.< /p>\n" +
                            "<p><br></p>\n" +
                            "<h2>Ai không nên dùng ACE?</h2>\n" +
                            "<p><strong>&bull; Phụ nữ mang thai</strong></p>\n" +
                            "<p>ACE có thể gây hại cho trẻ sơ sinh trong 6 tháng cuối của thai kỳ. Vì vậy, để bảo vệ bản thân và con bạn tốt hơn, tốt nhất bạn nên chọn phương pháp điều trị khác để kiểm soát huyết áp theo hướng dẫn của bác sĩ.</p> p>\n" +
                            "<p><strong>&bull; Người mắc bệnh thận</strong></p>\n" +
                            "<p>Vì ACE có thể làm giảm lượng máu cung cấp cho thận nên loại người này sử dụng nó sẽ không an toàn.</p>\n" +
                            "<p><strong>&bull; Người bị dị ứng nặng</strong></p>\n" +
                            "<p>Nếu dùng ACE gây ra bất kỳ phản ứng dị ứng nghiêm trọng nào cho cơ thể bạn, hãy ngừng dùng thuốc và tìm kiếm sự chăm sóc y tế ngay lập tức.</p>\n"
                )
            )
            listDrugsHyper.add(
                Info(
                    "2. Thuốc lợi tiểu",
                    "<p>Một loại thuốc điều trị tăng huyết áp phổ biến khác là thuốc lợi tiểu, giúp bài tiết natri (muối) và nước dư thừa để kiểm soát huyết áp. Thuốc này thường được áp dụng cùng với các loại thuốc khác trong điều trị huyết áp cao.</p>\n" +
                            "<p><br></p>\n" +
                            "<h2>Ai không nên dùng thuốc lợi tiểu?</h2>\n" +
                            "<p><strong>&bull; Người lớn tuổi</strong></p>\n" +
                            "<p>Họ có xu hướng phản ứng nghiêm trọng hơn với tình trạng mất nước, điều này cho thấy nên tránh dùng thuốc lợi tiểu.</p>\n" +
                            "<p><strong>&bull; Phụ nữ mang thai và cho con bú</strong></p>\n" +
                            "<p>Thuốc lợi tiểu có thể được truyền từ cơ thể mẹ sang con, do đó góp phần làm trẻ mất nước.</p>\n" +
                            "<p><strong>&bull; Trẻ con</strong></p>\n" +
                            "<p>Thuốc lợi tiểu có thể gây thiếu hụt canxi, dẫn đến suy giảm sự phát triển của xương.</p>\n"
                )
            )
            listDrugsHyper.add(
                Info(
                    "3. Thuốc ức chế thụ thể Angiotensin II (ARBS)",
                    "<p>Để minh họa cách ARB hoạt động nhằm hạ huyết áp, chúng ta phải đề cập đến angiotensin. Nó có thể thu hẹp các mạch máu khi liên kết ở vị trí thụ thể. ARBS ngăn cản sự liên kết của nó với các mạch máu, do đó, làm cho các mạch máu luôn mở và giảm huyết áp.</p>\n" +
                            "<p><br></p>\n" +
                            "<h2>AI không nên dùng ARB?</h2>\n" +
                            "<p><strong>&bull; Những người có vấn đề về thận</strong></p>\n" +
                            "<p>Những người bị thu hẹp động mạch đến thận (hẹp động mạch thận) hoặc chức năng thận rất kém có thể có phản ứng nghiêm trọng với ARB.</p>\n" +
                            "<p><strong>&bull; Những người có nồng độ natri trong máu thấp</strong></p>\n" +
                            "<p>ARBS có thể ức chế cơ chế của angiotensin II, do đó làm giảm natri trong ống thận và làm trầm trọng thêm tình trạng thiếu natri của bạn.</p>\n" +
                            "<p><strong>&bull; Phụ nữ mang thai và cho con bú &amp;#38; người bị dị ứng nặng</strong></p>\n" +
                            "<p>Những người đó cũng cần xin lời khuyên của bác sĩ về việc dùng ARB.</p>\n"
                )
            )
            listDrugsHyper.add(
                Info(
                    "4. Thuốc chẹn kênh canxi",
                    "<p>Bạn có thể thắc mắc làm thế nào việc kiểm soát huyết áp có thể liên quan đến canxi. Tuy nhiên, canxi có thể chảy vào các tế bào cơ của tim và động mạch, làm cho cơ co bóp mạnh hơn và cứng hơn. Thuốc chẹn kênh canxi hoạt động bằng cách chặn canxi vào mạch máu của bạn, giúp thư giãn các cơn co thắt cơ để hạ huyết áp.</p>\n" +
                            "<p><br></p>\n" +
                            "<h2>Ai không nên dùng thuốc chẹn kênh canxi?</h2>\n" +
                            "<p><strong>&bull; Người có vấn đề về tim</strong></p>\n" +
                            "<p>Nếu bạn bị suy tim sung huyết hoặc có vấn đề về cấu trúc tim, thuốc chẹn kênh canxi có thể làm giảm nhịp tim và gây hại cho cơ thể bạn</p>\n" +
                            "<p><strong>&bull; Phụ nữ mang thai và cho con bú &amp;#38; người bị dị ứng nặng</strong></p>\n" +
                            "<p>Những người đó cũng cần xin lời khuyên của bác sĩ về việc dùng thuốc chẹn kênh canxi.</p>\n"
                )
            )
            //
            listControl.add(
                Info(
                    "",
                    "Chiến đấu chống lại 'kẻ giết người thầm lặng' là một cam kết suốt đời. Đừng chỉ dựa vào thuốc và thực hiện một số thay đổi để cứu trái tim của bạn. Bằng cách làm theo lối sống lành mạnh cho tim trong bài viết này, bạn có thể thấy các con số của mình trở lại như cũ họ nên như vậy. Hãy thực hiện ngay bây giờ để giảm huyết áp và cải thiện cuộc sống hàng ngày."
                )
            )
            listControl.add(
                Info(
                    "1. Giữ cân nặng khỏe mạnh",
                    "Cân nặng và huyết áp có mối liên hệ chặt chẽ với nhau. Khi tăng cân, huyết áp cũng tăng theo. Giảm cân quá mức có thể làm giãn mạch máu khiến tim bơm máu tốt hơn. Nghiên cứu đã chứng minh rằng giảm cân có thể làm giảm huyết áp tâm thu xuống 4,5 đến 8,5 mmHg và số tâm trương từ 3,2 đến 6,5 mmHg.${
                        System.getProperty("line.separator")
                    }${System.getProperty("line.separator")}Điều đó không có nghĩa là bạn phải nỗ lực rất nhiều để giảm nhiều cân vì chỉ cần giảm 5% trọng lượng cơ thể cũng có thể tạo ra sự khác biệt trong việc hạ huyết áp. ${
                        System.getProperty(
                            "dòng.dấu phân cách"
                        )
                    } ${System.getProperty("line.separator")}Trong khi đó, bạn cần hết sức chú trọng đến việc theo dõi vòng eo của mình. Quá nhiều khối lượng xung quanh phần giữa của bạn có thể ảnh hưởng đến huyết áp của bạn. Người ta đề nghị nam giới nên giữ số đo vòng eo dưới 40 inch và nữ giới không quá 35 inch.${
                        System.getProperty(
                            "dòng.dấu phân cách"
                        )
                    } ${System.getProperty("line.separator")}"
                )
            )
            listControl.add(
                Info(
                    "2. Ăn chế độ DASH",
                    "<p>Chế độ ăn kiến cận cận để ức chế tăng áp áp (DASH) được tạo ra để khuếch đại, điều trị huyết áp cao và cải thiện cholesterol. Ngoài ra, nó còn hỗ trợ giảm cân, giảm nguy cơ ung thư thư, v.v. Chương trình ăn kiến DASH thông thường không khuyến khích nhiều hơn thế 1 dinh dưỡng phê phê (2.300 mg) natri mỗi ngày Bằng cách giảm lượng natri đưa vào cũng như tăng lượng kali, magie và canxi, chế độ ăn DASH có có thể làm giảm áp lực tâm trí của bạn tới 11mmHg.</p>\n" +
                            "<p><br></p>\n" +
                            "<p>Khi bạn mua bất kỳ sản phẩm nào, hãy học cách đọc nhãn để kiểm tra tỷ lệ natri. Nếu nồng độ natri vượt quá 20%, hãy dừng mua.</p>\n" +
                            "<p><br></p>\n" +
                            "<p> Nói chung, đóng góp theo chế độ ăn DASH bao gồm:</p>\n" +
                            "<p>&bull; Ăn trái cây, rau củ, ngũ cốc nguyên hạt và thịt nạc</p>\n" +
                            "<p>&bull;Dùng các sản phẩm từ sữa ít béo</p>\n" +
                            "<p>&bull;Hạn chế thực phẩm có chất béo bão hòa và chất béo chuyển hóa, có giới hạn như thực phẩm chế biến sẵn</p>\n" +
                            "<p>&bull; Cắt giảm đồ ăn và bán đồ đồ có đường</p>\n"
                )
            )
            listControl.add(
                Info(
                    "3. Hạn chế uống rượu",
                    "<p>Đáng ngạc nhiên là 16% trường hợp tăng huyết áp trên toàn thế giới có liên quan nhiều đến rượu. Uống nhiều hơn mức độ vừa phải có thể làm tăng huyết áp lên tới 7 mmHg.</p>\n" +
                            "<p><br></p>\n" +
                            "<p>Để bảo vệ tim và điều trị bệnh cao huyết áp tốt hơn, nam giới không nên uống nhiều hơn hai ly và phụ nữ cần hạn chế uống rượu ở mức một ly mỗi ngày. Để đo lượng rượu của bạn một cách trực quan hơn, một ly tương đương với 12 ounce bia , 5 ounce rượu vang hoặc 1,5 ounce rượu 80 độ.</p>\n" +
                            "<p><br></p>\n" +
                            "<p>Chú ý! Nếu bạn đang dùng thuốc điều trị tăng huyết áp, tốt nhất bạn nên ngừng uống rượu vì nó sẽ làm giảm hiệu quả.</p>\n"
                )
            )
            listControl.add(
                Info(
                    "4. Tập thể dục thường xuyên",
                    "Khi bạn tập thể dục thường xuyên, nhịp tim và nhịp thở sẽ tăng lên khiến tim bơm mạnh hơn, từ đó làm giảm huyết áp. Nếu bạn bị tăng huyết áp, hãy thử các bài tập aerobic, rèn luyện sức mạnh và các bài tập linh hoạt với cường độ vừa phải. 150 phút mỗi tuần có thể đáng kể cải thiện huyết áp của bạn hữu ích như một số loại thuốc. Bạn có thể chọn chạy bộ, đi bộ, bơi lội, tập tạ, yoga hoặc bất kỳ bài tập nào bạn muốn để khiến thói quen của bạn trở nên vui vẻ và khỏe mạnh." +
                            "" +
                            "Điều quan trọng là phải kiên trì vì nếu bạn ngừng tập thể dục, huyết áp của bạn có thể tăng trở lại."
                )
            )
            listControl.add(
                Info(
                    "5. Quản lý căng thẳng",
                    "<p>Nhịp tim của bạn tăng lên và các mạch máu co lại khi bạn ở trong trạng thái căng thẳng theo thời gian. Trong khi đó, khi bị căng thẳng, bạn có nhiều khả năng thực hiện các thói quen như uống rượu hoặc dùng thực phẩm chế biến sẵn có thể làm tăng huyết áp số.</p>\n" +
                            "<p><br></p>\n" +
                            "<p>Dưới đây là một số mẹo giúp bạn kiểm soát căng thẳng:</p>\n" +
                            "<p>&bull; Hãy xem xét những gì bạn có thể giải quyết. Nếu một nhiệm vụ làm bạn khó chịu, điều cần thiết là phải lập kế hoạch và giải quyết nó càng sớm càng tốt.</p>\n" +
                            "<p>Hãy thư giãn. Đã đến lúc thể hiện tình yêu thương và làm những điều bạn thích, cho dù đó là nghe nhạc, tập yoga hay đọc sách.</p>\n" +
                            "<p>&bull; Hãy chú ý tránh xa những thói quen tiêu cực như uống rượu hoặc hút thuốc.</p>\n"
                )
            )
            listControl.add(
                Info(
                    "6. Bỏ hút thuốc",
                    "<p>Mỗi làn khói thuốc lá sẽ khiến huyết áp tăng ngay lập tức và phản ứng mạnh mẽ của tim. Nếu bạn tiếp tục hút thuốc, nicotin sẽ làm hỏng thành mạch và đẩy nhanh các chất béo làm tắc nghẽn động mạch, gây tăng huyết áp. Chất nicotin cũng có thể ảnh hưởng tiêu cực đến thuốc. Hơn nữa, việc tiếp xúc với khói thuốc thụ động cũng sẽ gây ra tác dụng tương tự.</p>\n" +
                            "<p><br></p>\n" +
                            "<p>Vì các thành viên trong gia đình và sức khỏe tim mạch của bạn, đã đến lúc bạn nên bỏ thuốc lá.</p>\n"
                )
            )
            //
            listLower.add(
                Info(
                    "",
                    "Bạn đang tìm kiếm những phương pháp phòng ngừa, điều trị và kiểm soát bệnh cao huyết áp ban đầu không dùng thuốc? Vậy thì bạn đã đến đúng nơi. Bài viết này sẽ giới thiệu các bài tập thể chất giúp giảm huyết áp. Hãy thêm chúng vào thói quen hàng ngày của bạn, bạn có thể hãy xem bạn sẽ tạo ra sự khác biệt lớn như thế nào."
                )
            )
            listLower.add(
                Info(
                    "1. Tập thể dục nhịp điệu",
                    "<p>Tập thể dục nhịp điệu có thể là một cách hiệu quả để kiểm soát huyết áp cao. Người ta đã chứng minh rằng hoạt động thể dục nhịp điệu thường xuyên có thể góp phần làm tăng rõ rệt nhịp tim và nhịp thở, từ đó làm giảm độ cứng của mạch máu để máu có thể lưu thông dễ dàng hơn. Trung bình, tập thể dục nhịp điệu thường xuyên làm giảm huyết áp tâm thu khi nghỉ ngơi từ 5 đến 8 mmHg.</p>\n" +
                            "<p><br></p>\n" +
                            "<p>Xin lưu ý rằng bạn có thể mất hiệu quả nếu ngừng tập thể dục. Cần tập thể dục với cường độ vừa phải từ 5 đến 7 ngày mỗi tuần. Trong khi đó, bệnh nhân tăng huyết áp phải thực hiện tối thiểu 30 phút hoặc tối đa 60 phút tập thể dục nhịp điệu mỗi ngày. Nếu coi bài tập 30 phút là thử thách đối với mình, bạn có thể chia nó thành các đợt 10 phút.</p>\n" +
                            "<p><br></p>\n" +
                            "<h2>VÍ DỤ về hoạt động aerobic sử dụng nhóm cơ lớn</h2>\n" +
                            "<p>&bull;Leo cầu thang</p>\n" +
                            "<p>&bull; Đi bộ</p>\n" +
                            "<p>&bull; Chạy bộ</p>\n" +
                            "<p>&bull; Đi xe đạp</p>\n" +
                            "<p>&bull; Bơi&nbsp;</p>\n" +
                            "<p>&bull; Khiêu vũ</p>\n"
                )
            )
            listLower.add(
                Info(
                    "2. Rèn luyện sức đề kháng",
                    "<p>Ngoại trừ các bài tập aerobic, bạn cũng có thể bổ sung thêm bài tập rèn luyện sức đề kháng để mở rộng mạch máu, nói cách khác là cải thiện huyết áp một cách trực tiếp và không gây hại gì. Việc thực hiện thường cần có tạ hoặc thiết bị như máy tập sức đề kháng. Nên tập hai hoặc ba lần một tuần với cường độ vừa phải. Ngoài ra, không nên tập cùng một phần cơ hoặc tập các bài tập sức đề kháng liên tục. Việc thay đổi phần cơ thể sẽ giúp hạ huyết áp.</p>\n" +
                            "<p><br></p>\n" +
                            "<h2>BIỆN PHÁP THẬN TRỌNG</h2>\n" +
                            "<p>&bull; Tìm kiếm hướng dẫn chuyên nghiệp;</p>\n" +
                            "<p>&bull; Ít lặp lại là tốt nhất;</p>\n" +
                            "<p>&bull; Nghỉ 90 giây trở lên giữa mỗi hiệp;</p>\n" +
                            "<p>&bull;Bài tập kháng lực nên diễn ra ở tốc độ được kiểm soát nhưng không quá chậm;</p>\n" +
                            "<p>&bull; Đừng nín thở vì nó sẽ làm tăng huyết áp của bạn.</p>\n" +
                            "<p><br></p>\n" +
                            "<h2>VÍ DỤ về rèn luyện sức đề kháng với thiết bị</h2>\n" +
                            "<p>&bull; Ép ngực</p>\n" +
                            "<p>&bull; Nhấn vai</p>\n" +
                            "<p>&bull; Cơ tam đầu mở rộng</p>\n" +
                            "<p>&bull; Bắp tay cong</p>\n" +
                            "<p>&bull;Kéo xuống</p>\n" +
                            "<p>&bull; Phần mở rộng lưng dưới</p>\n" +
                            "<p>&bull; Cơ tứ đầu duỗi ra</p>\n" +
                            "<p>&bull; Nuôi bê</p>\n"
                )
            )
            listLower.add(
                Info(
                    "3. Tập luyện linh hoạt",
                    "<p>Các bài tập thể dục hoặc giãn cơ linh hoạt đơn giản mang lại lợi ích cho người bị tăng huyết áp. Khi bạn căng cơ và khớp, thực tế là bạn đang kéo giãn mạch máu và động mạch, đồng thời giảm độ cứng của lưu lượng máu. Đã đến lúc thêm hoạt động giãn cơ vào thói quen tập luyện của bạn.< /p>\n" +
                            "<p><br></p>\n" +
                            "<p>Theo các nghiên cứu, tập từ 2 đến 5 ngày mỗi tuần có thể làm giảm huyết áp. Trong khi đó, mỗi bài tập phải duy trì từ 10 đến 30 giây để đảm bảo hiệu quả.</p>\n" +
                            "<p><br></p>\n" +
                            "<p>VÍ DỤ về tính linh hoạt</p>\n" +
                            "<p>&bull;Yoga</p>\n" +
                            "<p>&bull; Pilates</p>\n"
                )
            )
            //
            listDiagnose.add(
                Info(
                    "",
                    "<p>Khi có các triệu chứng hạ huyết áp mãn tính, bạn có thể tò mò về cách mình sẽ được chẩn đoán và điều trị tại bệnh viện. Hãy xem những điều cần biết về chẩn đoán hạ huyết áp.</p>\n" +
                            "<p><br></p>\n" +
                            "<p>Bác sĩ của bạn có thể cần thực hiện các xét nghiệm dưới đây nếu nghi ngờ bạn bị hạ huyết áp:</p>\n"
                )
            )
            listDiagnose.add(
                Info(
                    "1. Xét nghiệm máu",
                    "Cách hiệu quả nhất để nắm bắt thông tin tổng thể về máu của bạn là làm xét nghiệm máu. Lượng đường trong máu và số lượng hồng cầu của bạn sẽ được thu thập để kiểm tra xem bạn có bị hạ huyết áp hay không."
                )
            )
            listDiagnose.add(
                Info(
                    "2. Kiểm tra bàn nghiêng",
                    "<p>Một phương pháp phổ biến khác để kiểm tra nghi ngờ hạ huyết áp thế đứng là kiểm tra bàn nghiêng. Bạn sẽ nằm ngửa nghỉ vài phút trước khi kiểm tra. Trong quá trình kiểm tra, bàn sẽ được nâng từ từ lên một góc thẳng đứng trong một thời gian. Trong thời gian chờ đợi , nhịp tim, huyết áp và các triệu chứng của bạn sẽ được đo định kỳ.</p>\n" +
                            "<p><br></p>\n" +
                            "<p>Bạn sẽ được coi là bị hạ huyết áp thế đứng nếu số tâm thu của bạn giảm 20mmHg hoặc chỉ số tâm trương giảm 10mmHg dưới mức cơ bản.</p>\n" +
                            "<p><br></p>\n" +
                            "<p>Nếu các triệu chứng xảy ra trong quá trình xét nghiệm, bệnh nhân phải được đưa về tư thế nằm ngửa ngay lập tức.</p>\n"
                )
            )
            listDiagnose.add(
                Info(
                    "3. Điện tâm đồ",
                    "<p>Để kiểm tra các vấn đề tiềm ẩn về tim có thể dẫn đến tụt huyết áp, không thể bỏ qua điện tâm đồ. Đây là một xét nghiệm không gây đau và không xâm lấn, trong đó các miếng vá (điện cực) mềm và dính được gắn vào da ngực, cánh tay của bạn , và chân để nhận tín hiệu điện của tim.</p>\n\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p>Mọi bất thường về nhịp tim, các vấn đề về cấu trúc trong tim, các vấn đề về cung cấp máu và oxy cho cơ tim hoặc các vấn đề về tim khác sẽ được phát hiện một cách chính xác thông qua xét nghiệm.</p>\n"
                )
            )
            //
            listDrugsHypo.add(
                Info(
                    "",
                    "Nếu các xét nghiệm của bạn cho kết quả dương tính và bạn có các triệu chứng, bạn có thể cần các liệu pháp y tế để chữa chứng hạ huyết áp. Vậy phương pháp điều trị huyết áp thấp là gì?"
                )
            )
            listDrugsHypo.add(
                Info(
                    "1. Fludrocortison",
                    "<p>Fludrocortisone là một loại thuốc có vẻ giúp ích cho hầu hết các loại bệnh huyết áp thấp. Nó làm tăng huyết áp bằng cách tăng lượng muối trong cơ thể, từ đó làm tăng thể tích máu.</p>\n" +
                            "<p><br></p>\n" +
                            "<h2>Tác dụng phụ bao gồm:</h2>\n" +
                            "<p>&bull; Đau đầu</p>\n" +
                            "<p>&bull; Buồn nôn</p>\n" +
                            "<p>&bull; Chóng mặt</p>\n" +
                            "<p>&bull; Mất ngủ</p>\n" +
                            "<p>&bull; Tăng huyết áp nằm ngửa</p>\n" +
                            "<p>&bull; Suy tim sung huyết</p>\n" +
                            "<p>&bull; Tăng nguy cơ lây nhiễm</p>\n" +
                            "<p><br></p>\n" +
                            "<h2>Ai không nên dùng?</h2>\n" +
                            "<p>&bull; Những người có vấn đề về phổi, gan, thận và tim nên tham khảo ý kiến bác sĩ trước khi dùng.</p>\n" +
                            "<p>&bull; Những người mắc bệnh ung thư, tăng huyết áp, tiểu đường hoặc có bất kỳ nguy cơ nhiễm trùng nào nên thận trọng.</p>\n" +
                            "<p>&bull; Ngoài ra, phụ nữ có thai và đang cho con bú cũng như những người bị dị ứng nặng cần xin lời khuyên của bác sĩ về việc dùng thuốc.</p>\n"
                )
            )
            listDrugsHypo.add(
                Info(
                    "2. Pyridostigmine",
                    "<p>Pyridostigmine sẽ cải thiện việc truyền tế bào thần kinh cho bệnh nhân hạ huyết áp thế đứng và kích hoạt phản xạ kiểm soát huyết áp, đặc biệt hữu ích cho bệnh nhân cao huyết áp khi nằm ngửa. Trong khi đó, tác dụng phụ của nó rất nhẹ và nhẹ.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<h2>Tác dụng phụ là:</h2>\n" +
                            "<p>&bull; Chuột rút cơ bụng</p>\n" +
                            "<p>&bull; Đi tiểu nhiều</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<h2>Ai không nên dùng?</h2>\n" +
                            "<p>&bull;Người bị tắc nghẽn đường ruột hoặc đường tiết niệu.</p>\n" +
                            "<p>&bull; Ngoài ra, phụ nữ có thai và đang cho con bú cũng như những người bị dị ứng nặng cần phải xin lời khuyên của bác sĩ về việc dùng thuốc.</p>\n"
                )
            )
            listDrugsHypo.add(
                Info(
                    "3. Midodrine",
                    "<p>Nó làm tăng huyết áp của bạn bằng cách kích hoạt các thụ thể của mạch máu để thu hẹp chúng. Midodrine có thể làm tăng huyết áp ngay cả khi bạn nghỉ ngơi. Vì vậy, chỉ nên sử dụng thuốc này nếu bạn bị huyết áp thấp nghiêm trọng ảnh hưởng đến huyết áp của bạn. cuộc sống hàng ngày hoặc các liệu pháp khác đều không có tác dụng.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<h2>Tác dụng phụ bao gồm:</h2>\n" +
                            "<p>&bull; ớn lạnh</p>\n" +
                            "<p>&bull;Tê bì</p>\n" +
                            "<p>&bull; Đau đầu</p>\n" +
                            "<p>&bull; Chóng mặt</p>\n" +
                            "<p>&bull; Buồn nôn</p>\n" +
                            "<p>&bull; Mệt mỏi</p>\n" +
                            "<p>&bull; Khó tiểu</p>\n" +
                            "<p>&bull; Đi tiểu nhiều</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<h2>Ai không nên dùng?</h2>\n" +
                            "<p>&bull; Midodrine có thể gây ra phản ứng nghiêm trọng khi kết hợp với một số loại thuốc. Do đó, hãy cho bác sĩ biết những loại thuốc, vitamin, chất bổ sung dinh dưỡng và sản phẩm thảo dược mà bạn đang dùng hoặc những gì bạn đã từng dùng trước khi dùng.</p >\n" +
                            "<p>&bull;Người mắc bệnh tiểu đường, có vấn đề về thị lực hoặc bệnh gan không được dùng thuốc midodrine để điều trị chứng hạ huyết áp.</p>\n" +
                            "<p>Những người sắp thực hiện bất kỳ loại phẫu thuật nào đều nên tham khảo ý kiến bác sĩ.</p>\n" +
                            "<p>&bull; Ngoài ra, phụ nữ có thai và đang cho con bú cũng như những người bị dị ứng nặng cần phải xin lời khuyên của bác sĩ về việc dùng thuốc.</p>\n"
                )
            )
            //
            listTipsHyper.add(
                Info(
                    "",
                    "Có thể có trường hợp huyết áp tăng đột ngột (huyết áp tâm thu đạt 180 và/hoặc huyết áp tâm trương đạt 120 mmHg) và cần được chăm sóc khẩn cấp. Sau khi gọi dịch vụ y tế ngay lập tức hoặc trong khi chờ xe cứu thương, điều gì có thể xảy ra? sơ cứu? Đừng lo lắng, chúng tôi đã chuẩn bị tất cả bí quyết cho bạn."
                )
            )
            listTipsHyper.add(
                Info(
                    "1. Bình tĩnh giúp giảm huyết áp",
                    "<p>Một điều mà tất cả bệnh nhân tăng huyết áp cần lưu ý là giảm căng thẳng có nghĩa là huyết áp sẽ giảm. Các nhà nghiên cứu đã phát hiện ra rằng thư giãn hoàn toàn trong vài phút có thể làm giảm huyết áp tâm thu từ 10 mmHg trở lên. Vì vậy, đó là tác dụng tốt nhất cách tự nhiên để giảm huyết áp khi bị tăng huyết áp khẩn cấp. Dưới đây là một số mẹo thư giãn bạn có thể thử:</p>\n" +
                            "<p>&bull; Dừng nhiệm vụ hiện tại của bạn</p>\n" +
                            "<p>&bull; Hít thở sâu</p>\n" +
                            "<p>&bull; Ngồi xuống hoặc nằm thẳng</p>\n" +
                            "<p>&bull; Nghe những âm thanh thư giãn</p>\n" +
                            "<p>&bull; Thiền</p>\n"
                )
            )
            listTipsHyper.add(
                Info(
                    "2. Uống thuốc huyết áp",
                    "Nếu bạn là bệnh nhân tăng huyết áp, bạn phải dùng thuốc huyết áp mà bác sĩ đã kê đơn. Thuốc là lựa chọn chính để điều trị huyết áp cao. Vì vậy, nếu bạn hoặc người nhà gặp phải tình trạng tăng huyết áp cấp cứu, đừng bao giờ quên uống điều trị y tế càng sớm càng tốt."
                )
            )
            listTipsHyper.add(
                Info(
                    "3. Nhâm nhi một tách trà dâm bụt",
                    "<p>Đừng ngạc nhiên. Nhiều nghiên cứu đã chứng minh rằng trà dâm bụt có thể có ảnh hưởng tích cực trực tiếp và gián tiếp đến huyết áp của bạn. Cũng hữu ích như một số phương pháp điều trị y tế, anthocyanin và một số chất chống oxy hóa trong hoa dâm bụt có thể làm giảm huyết áp của bạn. huyết áp. Uống trà dâm bụt có thể làm giảm chỉ số huyết áp tâm thu của bạn tối đa là 7 điểm.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p>Trong khi đó, nhấm nháp trà cũng có thể làm giảm huyết áp một cách gián tiếp bằng cách giảm căng thẳng. Trà êm dịu giúp bạn cảm thấy bình tĩnh hơn, kiểm soát cơn tức giận và hạ huyết áp.</p>\n"
                )
            )
            listTipsHyper.add(
                Info(
                    "4. Ăn sôcôla đen",
                    "<p>Ăn một lượng nhỏ sô cô la đen có thể giúp giảm huyết áp. Nó chứa catechin và Procyanidin, có thể làm giãn mạch máu. Theo nghiên cứu, cả chỉ số tâm thu và tâm trương đều giảm từ 2 đến 3 mmHg sau khi uống sô cô la đen. Mặc dù những thay đổi còn khiêm tốn nhưng hiệu quả tích cực là đúng.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p>Hơn nữa, sô cô la đen có thể gián tiếp ảnh hưởng tích cực đến tình trạng tăng huyết áp của bạn bằng cách giúp giải phóng endorphin giúp bạn bình tĩnh lại. Đã đến lúc dự trữ một ít để đề phòng.</p>\n"
                )
            )
            //
            listTipsHypo.add(
                Info(
                    "",
                    "Khi huyết áp của bạn rất thấp hoặc giảm nhanh, đó có thể là trường hợp cấp cứu y tế. Bước đầu tiên là liên hệ với bác sĩ để được điều trị y tế ngay lập tức và xem xét cẩn thận các loại thuốc của bạn. Nhưng bạn có thể làm gì khác? Hãy tìm hiểu các mẹo dưới đây và bạn sẽ không còn mù mờ nữa."
                )
            )
            listTipsHypo.add(
                Info(
                    "1. Nằm xuống",
                    "Khi bạn gặp bất kỳ triệu chứng hạ huyết áp nào, hãy cố gắng ngồi hoặc nằm trên một bề mặt phẳng một cách an toàn và ngay lập tức. Tiếp tục đứng có thể làm tình trạng hạ huyết áp tư thế của bạn trở nên tồi tệ hơn. Nằm hoặc ngồi có lợi cho việc bình thường hóa huyết áp của bạn."
                )
            )
            listTipsHypo.add(
                Info(
                    "2. Giữ đủ nước",
                    "Một trong những nguyên nhân phổ biến gây hạ huyết áp là mất nước. Vì vậy, uống nhiều nước hơn, bao gồm nước lọc, nước dừa và đồ uống thể thao, không chỉ có thể làm giảm tình trạng mất nước mà còn giúp duy trì lượng chất lỏng trong cơ thể. Ngoài ra, bạn có thể bổ sung thêm một ít chất lỏng. lượng muối hoặc đường để cải thiện huyết áp hoặc khôi phục mức đường huyết của bạn."
                )
            )
            listTipsHypo.add(
                Info(
                    "3. Uống đủ muối",
                    "<p>Hãy thử ăn nhiều thức ăn mặn hơn hoặc liếm một chút muối để natri làm tăng huyết áp. Bạn cũng có thể uống đồ uống thể thao hoặc uống muối bù nước (ORS) để bù nước và cung cấp muối cũng như các chất điện giải khác để cải thiện huyết áp của bạn.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p>Tuy nhiên, đừng dùng ORS nếu bạn là bệnh nhân tiểu đường và đảm bảo không ăn quá nhiều muối. Nó có thể dẫn đến các vấn đề như giữ nước cùng với huyết áp cao.</p>\n"
                )
            )
            listTipsHypo.add(
                Info(
                    "4. Mang vớ nén",
                    "<p><br />Những người bị hạ huyết áp thế đứng thường có tụ máu ở chân. Tất nén hoạt động bằng cách tạo áp lực nhẹ lên chân để dẫn máu về tim, điều này cho thấy phiên bản cao đến đùi hoặc cao đến eo sẽ có hiệu quả.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p>Xin lưu ý rằng một số bệnh nhân không phù hợp để đeo chúng. Vì vậy, việc tham khảo ý kiến bác sĩ là rất quan trọng.</p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<p>Bây giờ bạn đã biết phải làm gì khi bạn hoặc người khác bị hạ huyết áp. Tuy nhiên, đừng cố coi mình là chuyên gia. Hãy nhớ luôn đến gặp bác sĩ và không bao giờ tự dùng thuốc.</p>\n"
                )
            )
        }
    }
}