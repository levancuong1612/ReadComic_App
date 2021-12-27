package com.example.appreadcomic;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.appreadcomic.DBFireBase.DBTheLoai;
import com.example.appreadcomic.Model.CSTheLoai;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentHome extends Fragment {
    GridView gridView;
    ArrayList<TheLoai>list;
    TheLoaiAdapter theLoaiAdapter;
    DataBase db;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.fragment_home,container,false);

//        db= new DataBase(view.getContext(), "comic.sqlite", null,1);
//        db.QueryData("CREATE TABLE  IF NOT EXISTS TheLoai(ID INTEGER  PRIMARY KEY  AUTOINCREMENT," +
//                "hinhanh VARCHAR(200)," +
//                "title VARCHAR(50))");
//        db.QueryData("CREATE TABLE  IF NOT EXISTS Truyen(ID INTEGER  PRIMARY KEY  AUTOINCREMENT," +
//                "tenTruyen VARCHAR(100)," +
//                "hinhanh TEXT," +
//                "tacGia VARCHAR(100)," +
//                "soChuong VARCHAR(100)," +
//                "IDTheLoai INTEGER," +
//                "loaiTruyen VARCHAR(100)," +
//                "ketTruyen VARCHAR(50)," +
//                "gioiThieu TEXT)");
//        db.QueryData("CREATE TABLE  IF NOT EXISTS ChuongTruyen(ID INTEGER  PRIMARY KEY  AUTOINCREMENT," +
//                "tenchuong VARCHAR(200)," +
//                "noidung TEXT," +
//                "IDTruyen INTEGER)");
//        db.QueryData("DELETE FROM ChuongTruyen");
//        db.QueryData("DELETE FROM Truyen");

//        db.QueryData("INSERT INTO ChuongTruyen VALUES (null," +
//                "'Chương 1: Thời niên thiếu'," +
//                "'Tình yêu giống như lần đầu được nếm thử quả khế mới chín,\n" +
//                "Chua chua chát chát, nhưng không kìm được, vẫn muốn nếm thêm lần nữa.\n" +
//                "Trong quả khế chát xanh xanh nụ cười ngốc nghếch, ngọt ngào của anh, tình đầu thơ ngây, trong sáng của em lặng lẽ nảy mầm.\n" +
//                "Tôi tên là Thẩm Thiên Tình.\n" +
//                "Nếu nói đến cuộc đời tôi, chỉ e khó lòng kể hết trong một chốc một lát, sợ mọi người đọc sẽ thấy nặng đầu buồn ngủ, vậy nên tôi sẽ chỉ chọn vài trọng điểm để nói.\n" +
//                "Cái gọi là “cuộc đời tôi” thật ra cũng chẳng dài, tính đến nay mới có mười bốn năm, ba trăm hai mươi bảy ngày, tám giờ, năm giây mà thôi.\n" +
//                "Đầu tiên, cũng giống như tất cả mọi người, tôi có cha mẹ rất hiền từ, lại có một người anh vô cùng đẹp trai, ưu tú, khiến các nữ sinh nhìn thấy đều không kìm được mà hét lên ngưỡng mộ.\n" +
//                "Về phần tôi, từ nhỏ đến lớn, nhận xét của các giáo viên nhìn chung đều quanh đi quẩn lại những từ như: cá tính, bốc đồng, bướng bỉnh, khó bảo, thích quản chuyện người khác, vân vân và vân vân… Giáo viên nào tốt một chút sẽ nói tôi hoạt bát, hướng ngoại, giữa đường gặp chuyện bất bình chẳng tha.\n" +
//                "Nhưng thế thì có gì khác chứ? Chẳng qua chỉ đổi cách nói cho dễ nghe hơn thôi, vẫn khiến tôi bị tổn thương.\n" +
//                "Cái gì? Bạn không tin ư?! Để tôi giải thích cho mà nghe nhé!\n" +
//                "Hoạt bát, hướng ngoại: nghĩa là tôi rất nghịch, nghịch vô cùng, nghịch đến mức bị phạt đánh.\n" +
//                "Giữa đường gặp chuyện bất bình chẳng tha: nói cách khác, chính là gây chuyện thị phi, nghịch ngợm, phá phách.\n" +
//                "Tôi hận nhất là năm lớp năm, cô giáo chủ nhiệm còn ghi trong sổ liên lạc của tôi là: ngu muội dốt nát, thiếu tôn trọng bậc bề trên, hung hăng càn quấy, không biết hối cải, mong phụ huynh quản lý nghiêm ngặt hơn, tránh gây hại cho nếp sống đẹp của xã hội.\n" +
//                "Vậy là cô ấy đã trang trọng biến tôi thành con quỷ phá hoại thế giới, làm băng hoại nếp sống xã hội, sự khởi sắc hay tụt dốc của nền kinh tế cũng liên quan đến tôi, hơn nữa, ngay cả việc Khổng Minh tiên sinh “xuất sư vị tiệp thân tiên tử[1]”, sự thành bại, thịnh suy của Trung Quốc trong năm nghìn năm qua cũng đều là lỗi của tôi, chỉ thiếu nước bắt tôi mổ bụng tự sát để tạ tội với thế giới.\n" +
//                "[1] Trong bài thơ Thục tướng của Đỗ Phủ viết về Gia Cát Lượng có câu: “Xuất sư vị tiệp thân tiên tử. Trường sử anh hùng lệ mãn khâm”, tạm dịch: Ra quân chưa kịp chiến thắng thân đã khuất, khiến người đời sau cũng phải chạnh lòng, nước mắt đẫm đầy vạt áo. Có nghĩa là chưa kịp thành công thì người đã chết.\n" +
//                "Tôi chẳng qua chỉ đặt biệt danh “Diệt Tuyệt sư thái[2]” sau lưng cô giáo quá lứa lỡ thì đó, ngoài ra còn cá cược với bạn bè trong lớp về màu sắc nội y của cô, mọi người thử nói xem, như thế có được coi là phạm tội chết ngàn lần không?\n" +
//                "[2] Nhân vật hư cấu trong tác phẩm Ỷ thiên đồ long ký của nhà văn Kim Dung, là chưởng môn nhân đời thứ ba của phái Nga Mi, nổi tiếng là người cứng nhắc, giáo điều, định kiến nặng nề.\n" +
//                "Mẹ phạt tôi quỳ cũng chẳng sao; muốn tôi ngày mai xin lỗi Diệt Tuyệt… à, cô giáo Ngô thì tôi cũng đồng ý; viết bản kiểm điểm bày tỏ sự ăn năn, hối hận lại càng là chuyện nhỏ, đảm bảo sẽ rất chân thành và đặc sắc gần bằng “Thư ly biệt vợ”; nhưng… điều tôi không thể chấp nhận nhất là mẹ lại không cho phép tôi ăn tối, đã thế còn cố ý nấu món thịt kho tàu “đầu sư tử”[3] mà tôi thích nhất nữa chứ!\n" +
//                "[3] Thịt viên lớn, có kích thước khoảng 5 – 10 cm.\n" +
//                "Đây đúng là hình phạt vô nhân đạo nhất trên đời này!\n" +
//                "Có điều, vẫn còn may, anh trai luôn bảo vệ tôi bất cứ lúc nào.\n" +
//                "Hồi nhỏ, nhiều lần bị phạt, tôi thường hờn tủi hỏi mẹ: “Mẹ ơi, con không phải do mẹ sinh ra đúng không?”\n" +
//                "“Đúng rồi đấy! Con được móc lên từ cống rãnh hôi thối.” Thật quá đáng! Sao mẹ trả lời dứt khoát như vậy? Lại còn làm vẻ mặt “Cả đời con, lúc này là thông minh nhất đấy!” nữa chứ.\n" +
//                "Trái ngược với tôi, anh trai là người tài đức vẹn toàn, rất đáng ngưỡng mộ. Mà quả thật tôi cũng sùng bái anh vô cùng.\n" +
//                "Khi ấy, điều kiện kinh tế nhà tôi không tốt lắm, gia đình làm nghề nông, cha mẹ hằng ngày đầu tắt mặt tối, không thể chăm sóc tôi chu đáo, tôi chẳng khác nào do một tay anh trai nuôi lớn. Đối với tôi, anh trai không chỉ là anh trai mà còn là người hiểu tôi nhất trên thế gian này. Không giống những người khác luôn phê phán, chỉ trích tôi, anh đối xử với tôi hoàn toàn khác, bao dung mọi hành vi của tôi. Mỗi lần tôi gây chuyện, giữa vô vàn cặp mắt chau lại nhìn tôi, luôn có một khuôn mặt mỉm cười, ánh mắt tràn đầy sự bao dung, thấu hiểu, âm thầm ủng hộ tôi.\n" +
//                "Ngay từ khi còn rất bé, tôi đã biết anh trai là người vô cùng quan trọng với tôi. Anh vừa là thần hộ mệnh vừa là nơi tránh nạn của tôi, mỗi lần xảy ra chuyện gì, người đầu tiên chạy đến bên tôi bao giờ cũng là anh; mỗi khi gây họa, tôi cũng tìm đến anh trước nhất. Từ rất lâu rồi, tôi đã nhận thức được rằng mình có thể mất đi tất cả nhưng không thể không có anh trai.\n" +
//                "Có lần, cực kỳ buồn chán vì không có việc gì làm, tôi ngồi xổm một bên xem đám bạn hàng xóm chơi trò “cô dâu, chú rể”, sau khi về nhà thì luôn miệng kêu gào đòi được gả cho anh trai. Trong những năm tháng trẻ thơ vô tri, hồ đồ đó, tôi cũng không hiểu từ “gả” có nghĩa là gì, nhưng Đại Mao – anh bạn hàng xóm hơn tôi hai tuổi đã nói với tôi mội cách rất người lớn rằng, “gả” có nghĩa là sống bên người mình thích nhất, mãi mãi không lìa xa.\n" +
//                "Người mà mình thích nhất? Đó chẳng phải là anh trai ư?\n" +
//                "Cho nên tôi mới hỏi anh trai có muốn được “gả” cho tôi không.\n" +
//                "Anh trai nói không được.\n" +
//                "“Tại sao?”\n" +
//                "“Bởi vì anh là con trai, không thể “gả” cho em được.”\n" +
//                "“Vậy, gả em cho anh là được chứ gì.”\n" +
//                "“Vẫn không được.”\n" +
//                "“Tại sao?” Lần đầu tiên tôi cảm thấy anh trai thật lắm chuyện, bèn ra sức trừng mắt với anh.\n" +
//                "\n" +
//                "Anh đột nhiên không nói gì, ôm chặt tôi vào lòng.\n" +
//                "\n" +
//                "Rất lâu, rất lâu sau đó, tôi mới hiểu được cái ôm đó chính là sự đau lòng.\n" +
//                "\n" +
//                "Dần dần hiểu chuyện, rốt cuộc tôi đã biết cô nhi thực chất là thế nào, cũng hiểu được sự thương xót trong cái ôm đó, song tôi cũng chẳng có cảm giác gì nhiều, vì chưa kịp buồn phiền thì đã có quá nhiều cảm giác len lỏi trong trái tim rồi, đầy đến nỗi không còn chỗ để chứa cái khác.\n" +
//                "\n" +
//                "Rốt cuộc có đúng là được móc lên từ cống rãnh hôi thối hay không, tôi chẳng muốn chứng minh, bởi vì cho dù không có thứ gì, tôi vẫn có một người thực sự yêu thương tôi hết mực, không bao giờ cô đơn.\n" +
//                "\n" +
//                "Anh trai, thật sự không chỉ là anh trai.\n" +
//                "\n" +
//                "Vậy thì là cái gì? Tôi vẫn chưa có đáp án, nhưng trước ngày hôm đó, tôi đã vô thức giấu hết số thư tình mà những người ngưỡng mộ nhờ tôi gửi cho anh trai.\n" +
//                "\n" +
//                "Năm lớp bốn, cô bạn thân nhất của tôi khen anh đẹp trai, toàn mượn cớ đến nhà tôi chơi, thế là trước khi kết thúc học kỳ đó, tôi đã cắt đứt quan hệ với nó, chính thức tuyệt giao, đồng thời hiểu ra một đạo lý ngàn năm không đổi, đó là: tình bạn giữa nữ giới tương đối mong manh.\n" +
//                "\n" +
//                "Năm lớp bảy, hoa khôi lớp tôi theo đuổi anh trai, tôi yêu cầu anh không được đến trường đón tôi nữa, tôi sẽ tự về nhà. Anh cử tưởng tôi không thích dựa dẫm vào anh vì muốn làm ra vẻ “người nhớn”.\n" +
//                "\n" +
//                "Đùa chứ! Sao tôi có thể để anh trai khôi ngô, tuấn tú của mình hằng ngày bị một bầy con gái si tình dùng ánh mắt cưỡng bức được?\n" +
//                "\n" +
//                "Anh trai là của tôi, của riêng mình tôi thôi!\n" +
//                "\n" +
//                "Ham muốn chiếm hữu anh trai của tôi khá cao, điều này tôi chưa từng phủ nhận.\n" +
//                "\n" +
//                "Dần dần tôi bắt đầu hiểu ra, đằng sau ham muốn chiếm hữu mãnh liệt này là gì. Đó chính là tình cảm của một thiếu nữ mười bốn tuổi…'," +
//                "7)");
//
//        db.QueryData("INSERT INTO ChuongTruyen VALUES(null," +
//                "'Chương 2: Hàn vũ'," +
//                "'Tôi tên là Thẩm Hàn Vũ.\n" +
//                "\n" +
//                "Cuộc đời tôi thực ra cũng chẳng có gì cao trào, kịch tính, tất cả đều xoay quanh một trọng tâm duy nhất là cô gái có tên Thẩm Thiên Tình.\n" +
//                "\n" +
//                "Cái gọi là “cuộc đời tôi” thật ra cũng chẳng dài, tính tới bây giờ mới được mười bảy năm, hai trăm bốn mươi ngày, chín giờ, ba mươi lăm phút, chín giây mà thôi.\n" +
//                "\n" +
//                "Từ nhỏ tới lớn, nhận xét của giáo viên về tôi nói chung đều quanh đi quẩn lại mấy từ như: phẩm hạnh tốt, biểu hiện xuất sắc, ham học, có chí tiến thủ. Kỳ thực, đó cũng chỉ là vì hoàn cảnh gia đình, muốn lĩnh học bổng ấy mà.\n" +
//                "\n" +
//                "Giống như tất cả mọi người, tôi có một người cha và một người mẹ luôn tôn trọng lẫn nhau, còn có một cô em gái vô cùng đáng yêu, hoạt bát nhưng lại không thích được khen là đáng yêu, vì đó là biểu hiện của sự non nớt, chưa trưởng thành, cũng không thích được khen là hoạt bát vì bệnh nghi ngờ của nó rất nặng, cho rằng như thế là có ý mắng nó nghịch ngợm.\n" +
//                "\n" +
//                "Cậu con trai đầu tiên thổ lộ tình cảm với nó đã hy sinh oanh liệt.\n" +
//                "\n" +
//                "Tình hỏi cậu ta thích con bé ở điểm gì?\n" +
//                "\n" +
//                "Ai dè cậu ta trả lời: “Vì cậu rất đáng yêu, hoạt bát.”\n" +
//                "\n" +
//                "Không khó để tưởng tượng anh chàng này sẽ chết thảm thế nào, nhỉ?\n" +
//                "\n" +
//                "Tình thấy cậu con trai đó thật đáng ghét, dám dùng cách này để chế giễu nó.\n" +
//                "\n" +
//                "Còn tôi lại cảm thấy con bé bị mắc chứng sợ mơ mộng hão huyền.\n" +
//                "\n" +
//                "Lần đầu tiên cô em gái được tỏ tình, kết quả là đối phương bị đánh bẹp đầu, xin hỏi tôi nên có phản ứng gì?\n" +
//                "\n" +
//                "Rất xin lỗi, tối hôm đó tôi cười đến sái quai hàm, không rảnh để phát biểu cảm tưởng.\n" +
//                "\n" +
//                "Tình nhà chúng tôi không giống những đứa trẻ khác, nó là một loài hoa lạ, từ nhỏ đã sôi… sôi nổi! (Từ này không phạm vào điều cấm kỵ của nó nhỉ?) Một đứa trẻ khỏe khoắn, hiếu động, chẳng có lấy một khắc ngồi yên, vừa biết bò đã chui khắp phòng, sau khi biết đi, đừng hòng con bé ngồi một chỗ, loáng một cái lại phải đi tìm nó khắp nơi.\n" +
//                "\n" +
//                "Con bé rất thích chơi trò trốn tìm, chui bên đông, trốn bên tây; bắt người khác đi kiếm, nhưng rất lạ, cho dù nó trốn ở đâu tôi vẫn mò ra được, người đầu tiên tìm thấy nó luôn là tôi.\n" +
//                "\n" +
//                "Điều khác thường nhất là, có một năm thu hoạch ruộng, cha mẹ không yên tâm để hai đứa trẻ ở nhà, liền đem chúng tôi theo, lúc đó Tình đã biết bò, đang học đi, cà ngày bò đi bò lại, hãnh diện thể hiện thành quả, không biết loay hoay thế nào lại bò lên đống rạ đầy, cuối cùng không xuống được. Chẳng ai biết rốt cuộc làm thế nào mà con bé bò lên được trên đó, người lớn cũng không biết phải cứu kiểu gì. Nghe nói, nếu mà ngã từ độ cao đó xuống thì đủ khiến đứa trẻ chưa đầy một tuổi không biết trời cao đất dày ấy phải bỏ mạng.\n" +
//                "\n" +
//                "Tuổi thơ của con bé chỉ toàn là mạo hiểm và kích động.\n" +
//                "\n" +
//                "Thiên Tình là do một tay tôi chăm bẵm, có thể nói, tôi là người thân thiết nhất của nó, không ai có thể hiểu con bé hơn tôi, khi nó bi bô tập nói, từ đầu tiên biết gọi không phải cha, cũng không phải mẹ, mà là anh.\n" +
//                "\n" +
//                "Tên đầu tiên con bé nhớ là Thẩm Hàn Vũ.\n" +
//                "\n" +
//                "Đói, mệt, bị thương, té ngã, chịu oan ức… nó chỉ biết đến tìm anh trai.\n" +
//                "\n" +
//                "Còn nhớ có một năm, nó đi lạc, cả nhà lo lắng phát điên, ra sức tìm kiếm, cuối cùng nhận được điện thoại của sở cảnh sát, liền vội vàng tới nơi, con bé ăn no uống đủ, vừa nhìn thấy tôi liền lao ngay vào vòng tay, cười ngô nghê rồi ngủ thiếp đi.\n" +
//                "\n" +
//                "Nhân viên cảnh sát kể cho cha mẹ tôi: “Cô bé này nói chưa sõi, hỏi cái gì cũng không biết, nhà có những ai, chỉ trả lời “anh”, hỏi cha mẹ tên gì nó cũng không nói được, bản thân tên gì càng không biết, kỳ lạ là tên anh trai thì lại nhớ rất rõ, cũng may nó nhớ, nếu không chúng tôi thực sự không biết làm thế nào. Cô bé ăn uống xong liền khóc toáng lên đòi tìm anh, khiến mọi người chúng tôi được một phen rối loạn.”\n" +
//                "\n" +
//                "Sau một phen hú hồn, con bé ngủ ngoan, say giấc nồng trong vòng tay tôi, hoàn toàn không để ý tới những người lớn vì nó mà nhốn nháo, cứ như chỉ cần có tôi ở bên, trời long đất lở cũng chẳng làm nó hốt hoảng.\n" +
//                "\n" +
//                "Con bé là bảo bối của tôi. Tôi luôn cho rằng mình có thể bảo vệ, yêu thương nó như thế, cho đến khi giao nó vào tay người đàn ông khác để anh ta tiếp tục nhiệm vụ bảo vệ, yêu thương con bé.\n" +
//                "\n" +
//                "Cho tới năm bảy tuổi, vô tình nghe thấy cha mẹ nói chuyện, giữa tôi và con bé đã có sự thay đổi, em gái không còn chỉ là em gái…\n" +
//                "\n" +
//                "Con bé non dại, không hiểu hoàn cảnh đáng thương của mình, nhưng tôi lại đau lỏng thay nó, thương đứa bé không có gì cả.\n" +
//                "\n" +
//                "Tôi tự nhủ với mình, sẽ đối tốt với con bé gấp bội, sẽ bù đắp toàn bộ những thiếu sót của ông trời.\n" +
//                "\n" +
//                "Tình rất vui vẻ, còn vui vẻ hơn tôi tưởng. Tính cách lạc quan, cởi mở khiến con bé lúc nào cũng nở nụ cười rạng rỡ, vô lo. Chưa từng thấy nó vì cái gì mà đau lòng tới mức không thể vượt qua.\n" +
//                "\n" +
//                "Dù gây họa bị phạt, dù tất cả mọi người đều không hiểu nó, chỉ cần tôi hiểu là đủ.\n" +
//                "\n" +
//                "Chỉ cần tôi hiểu, con bé liền cười.\n" +
//                "\n" +
//                "Khi Tình học lớp năm, cô giáo chủ nhiệm tố hết tội trạng của nó trong sổ liên lạc, mẹ có vẻ rất tức giận, nhưng tôi biết Tình không nghịch ngợm như họ nghĩ, nó không phải đứa bé trẻ vô cớ gây chuyện, nhất định có nguyên nhân gì đây.\n" +
//                "\n" +
//                "Tôi lặng lẽ chuẩn bị sẵn bữa tối cho con bé, hỏi nó vì sao lại lấy gương để soi dưới váy cô chủ nhiệm.\n" +
//                "\n" +
//                "Tình trả lời: “Em ghét cô ấy!”\n" +
//                "\n" +
//                "“Được, Tình ghét, anh cũng ghét. Nhưng có thể nói cho anh biết vì sao không?”\n" +
//                "\n" +
//                "“Cô ấy vu oan cho em!” Tình nhệch miệng, nước mắt dâng lên.\n" +
//                "\n" +
//                "Vu oan? Tôi nhíu mày: “Cô ấy vu oan cho em cái gì?”\n" +
//                "\n" +
//                "“Cả lớp đều ghét cô ấy, có người bỏ con gián vào cốc trà của cô ấy, tìm không ra thủ phạm, cô ấy liền đổ lên đầu em. Chẳng lẽ vì em thường hay gây chuyện mà việc xấu nào cũng nhất định là do em làm ư? Sao có thể như vậy chứ?”\n" +
//                "\n" +
//                "Giọng nói lộ rõ sự tủi thân, non nớt, con bé không thể hiểu, cũng không thể chấp nhận sự đối xử thiên vị.\n" +
//                "\n" +
//                "“Tình, em dậy đi!” Tôi sẽ không để em gái phải chịu phạt oan ức. “Ăn cơm xong còn đi tắm rồi ngủ, ngày mai anh đưa em đến trường.”\n" +
//                "\n" +
//                "“Nhưng mẹ…”\n" +
//                "\n" +
//                "“Anh sẽ nói với mẹ giúp em. Nhưng em làm thế cũng không đúng, biết không Tình? Cho dù em ghét cô giáo cỡ nào, cũng không thể làm như vậy, hiểu không?”\n" +
//                "\n" +
//                "Con bé gật đầu: “Anh có cảm thấy em là đứa trẻ hư không?”\n" +
//                "\n" +
//                "“Đương nhiên không phải!” Con bé là do tôi trông nom từ nhỏ, sao tôi không hiểu nó chứ? Nó chưa bao giờ hư, chỉ là sự hoạt bát gây cho nó nhiều khó khăn, mạo hiểm hơn người khác, tính tình lại ngay thẳng, yêu ghét rõ ràng, không giả dối.\n" +
//                "\n" +
//                "Tôi chưa bao giờ cho rằng như thế có gì không tốt, thậm chí còn hy vọng con bé mãi mãi giữ được sự ngây thơ như vậy. “Anh trai là tốt nhất, người khác không hiểu cũng chẳng sao, chỉ cần anh hiểu là được rồi.” Con bé hay nói câu này nhất.\n" +
//                "\n" +
//                "Điều đó khiến tôi hiểu rằng, nó coi tôi quan trọng hơn cả cha mẹ, hơn tất cả mọi người thậm chí là chính bản thân nó. Nó có thể bình tĩnh tiếp nhận việc mình là trẻ mồ côi bởi vì nó còn có tôi.\n" +
//                "\n" +
//                "Trong lòng nó có thể không có cha, không có mẹ, không làm con gái nhà họ Thẩm, nhưng không không thể không có tôi.\n" +
//                "\n" +
//                "Điều này đã vượt quá mối quan hệ anh trai, em gái thông thường. Đó không chỉ là tình cảm anh em đơn thuần nữa, mà còn có thêm nhiều ràng buộc, nhiều sự lưu luyến không nỡ rời.\n" +
//                "\n" +
//                "Khi nhìn thấu điểm này, con bé đã là sự ràng buộc và trách nhiệm không thể từ bỏ cả đời này của tôi, vì ngày hôm đó, tôi đã ngoắc tay với con bé, hữa sẽ mãi mãi bên nhau. '," +
//                "7)");
//
//        db.QueryData("INSERT INTO ChuongTruyen VALUES(null," +
//                "'Chương 3: Lời hứa'," +
//                "'“Tình!” Trên đường từ trường về, thái độ phớt lờ của em gái khiến lòng Thẩm Hàn Vũ đầy nghi hoặc.\n" +
//                "\n" +
//                "Anh theo cô vào phòng, thấy cô lấy sách ra, liền quan tâm bước lên phía trước hỏi: “Viết bài à? Có cần anh dạy không?”\n" +
//                "\n" +
//                "“Không cần, em tự viết, anh đi ra đi!”\n" +
//                "\n" +
//                "Thẩm Hàn Vũ ngẩn người. Đây là lần đầu tiên cô bé xua đuổi anh. Trước nay cô chỉ biết quấn lấy anh, chưa từng như thế này bao giờ.\n" +
//                "\n" +
//                "Hôm nay rốt cuộc cô bé làm sao vậy nhỉ?\n" +
//                "\n" +
//                "“Tình…”\n" +
//                "\n" +
//                "“Em không rảnh!” Cô giơ cao quyển sách, che mặt.\n" +
//                "\n" +
//                "“Nhưng…”\n" +
//                "\n" +
//                "“Đừng làm ồn!”\n" +
//                "\n" +
//                "“Anh muốn nói là…”\n" +
//                "\n" +
//                "“Phiền phức quá, không thấy em đang học à?” Cô bé đặt sách xuống, gân cổ hét to.\n" +
//                "\n" +
//                "Anh thở dài: “Anh chỉ muốn nhắc là em cầm ngược sách rồi.”\n" +
//                "\n" +
//                "Cô cúi đầu nhìn một cái rồi ngẩng lên trợn mắt với anh, phồng má, không nói nên lời.\n" +
//                "\n" +
//                "Biểu hiện này khiến anh bật cười.\n" +
//                "\n" +
//                "Chỉ cần tức giận là hai má Tình liền đỏ lựng, giống như quả táo đỏ, khiến người ta muốn cắn một cái.\n" +
//                "\n" +
//                "“Cười cái gì mà cười! Cười cho đứt ruột mà chết đi! Anh tưởng là học sinh gương mẫu thì ghê gớm lắm à?” Trong cơn tức giận, quyển sách liền bay vèo tới chỗ anh, tròng mắt cô đỏ hoe, tủi thân muốn khóc.\n" +
//                "\n" +
//                "Thẩm Hàn Vũ không cười được nữa, hoảng sợ hỏi: “Sao vậy? Nói khóc liền khóc luôn à?”\n" +
//                "\n" +
//                "“Sao em phải nói với anh? Đi ra đi!” Thiên Tình hờn dỗi hất cánh tay vỗ về của anh.\n" +
//                "\n" +
//                "Thẩm Hàn Vũ nhìn chằm chằm vào cánh tay bị đẩy ra, trong phút chốc không kịp phản ứng.\n" +
//                "\n" +
//                "Xem ra tâm trạng cô bé thực sự rất xấu. Anh cũng không thèm chấp, gật đầu, nhân nhượng cô: “Được rồi, vậy em đọc sách đi, anh ra ngoài, không làm phiền em nữa.”\n" +
//                "\n" +
//                "Anh nhặt quyển sách lên, đặt vào tay cô bé. Cô ngơ ngẩn nhìn theo bóng dáng anh dần biến mất sau cánh cửa, không dám quát anh, chỉ có thể chán nản đập mạnh cặp xách.\n" +
//                "\n" +
//                "“Đồ ngốc! Thẩm Hàn Vũ là đồ đại ngốc…”\n" +
//                "\n" +
//                "Không khí căng thẳng kéo dài tới bữa tối, đến cha mẹ cũng cảm thấy điều bất thường ở hai đứa.\n" +
//                "\n" +
//                "Thiên Tình bình thường lắm lời, đột nhiên lưỡi như bị mèo ăn trộm mất, lặng lẽ không nói gì, chẳng trách ai tin được chứ?\n" +
//                "\n" +
//                "“Tiểu Tình, hôm nay con thấy khó chịu à?” Cha mẹ quan tâm hỏi han.\n" +
//                "\n" +
//                "“Không ạ.” Cô bé cúi đầu và cơm.\n" +
//                "\n" +
//                "Một ánh mắt thân thiết nhìn cô chằm chằm, cô cảm nhận được nhưng cố chấp không thèm đáp lại.\n" +
//                "\n" +
//                "“Món thịt kho tàu “đầu sư tử” em thích ăn nhất đây…” Thẩm Hàn Vũ theo thói quen gắp cho cô.\n" +
//                "\n" +
//                "“Tự em gắp được, không cần anh nhiều chuyện!” Cô chẳng thèm nhìn, đẩy bát đi.\n" +
//                "\n" +
//                "Đôi đũa sững lại giữa không trung, anh lúng túng ngây người.\n" +
//                "\n" +
//                "“Tiểu Tình, sao con có thể nói với anh như vậy!” Mẹ nghiêm mặt quở trách.\n" +
//                "\n" +
//                "“Mẹ, không sao mà…” Thẩm Hàn Vũ cười gượng gạo, muốn xoa dịu tình hình.\n" +
//                "\n" +
//                "“Cái gì mà không sao, Tiểu Tình, xin lỗi anh con đi!”\n" +
//                "\n" +
//                "“Con không muốn!” Cô giận dỗi cãi lại.\n" +
//                "\n" +
//                "“Mẹ nói là xin lỗi anh đi, Thẩm Thiên Tình!”\n" +
//                "\n" +
//                "“Mẹ, thực sự không cần…”\n" +
//                "\n" +
//                "“Thẩm Hàn Vũ, không cần lòng tốt giả tạo của anh.”\n" +
//                "\n" +
//                "“Thẩm Hàn Vũ là để cho con gọi à? Không biết lớn nhỏ, nó là anh con đấy! Đừng ỷ mình ít tuổi mà tùy tiện, khi anh trai ở tuổi con, hiểu chuyện hơn con gấp trăm lần!”\n" +
//                "\n" +
//                "“Tiểu Tình, con xin lỗi đi! Lần này là con không đúng.” Đến người cha vốn ít nói cũng lên tiếng.\n" +
//                "\n" +
//                "Cô uất ức đầy bụng, đặt mạnh đũa xuống: “Con biết anh cái gì cũng đúng, cái gì cũng tốt, con thì việc gì cũng làm không tốt, chỉ để thầy cô giáo mách tội, mất mặt cha mẹ. Không cần cha mẹ phải nhắc lại. Dù sao con cũng là người thừa trong cái nhà này, cha mẹ có anh để làm niềm tự hào như vậy là được rồi!”\n" +
//                "\n" +
//                "Nói xong, cô đẩy ghế, quay người chạy ra ngoài.\n" +
//                "\n" +
//                "Còn lại ba người ngồi sững sờ bên bàn ăn.\n" +
//                "\n" +
//                "“Nói xằng bậy gì vậy?” Mẹ nhíu mày. “Đầu con bé này có chỗ nào không ổn à?”\n" +
//                "\n" +
//                "Thẩm Hàn Vũ cắn môi không nói, nhìn theo bóng cô dần mất hút, nhíu mày suy nghĩ.\n" +
//                "\n" +
//                "Là do tài năng của anh quá bộc lộ, làm tổn thương lòng tự tôn của cô bé chăng?\n" +
//                "\n" +
//                "Cô luôn tỏ ra cởi mở, thoải mái, anh chưa từng nghĩ sự nổi bật quá mức của mình có thể tạo áp lực cho cô. Là ai đem hai đứa ra so sánh, khiến cô bị tổn thương?\n" +
//                "\n" +
//                "“Các con cãi nhau à?” Cha quan tâm hỏi, có chậm hiểu cũng thấy rõ sự khác thường.\n" +
//                "\n" +
//                "Đây quả là một việc kỳ lạ, chẳng phải thường ngày tình cảm của hai anh em tốt tới mức khiến người ta ghen tỵ sao? Hai đứa cũng có lúc giận dỗi ư?\n" +
//                "\n" +
//                "“Không ạ. Cha đừng lo con sẽ giải quyết việc này.”\n" +
//                "\n" +
//                "“Con ấy à, đừng có nuông chiều nó quá, con bé này coi trời bằng vung rồi!” Mẹ lắc đầu, thở dài nói.\n" +
//                "\n" +
//                "Ánh mắt chuyển sang chỗ trống bên cạnh, bát cơm bỏ lại trên bàn, chưa ăn được mấy miếng, anh nói khẽ: “Tình không phải như vậy đâu.”\n" +
//                "\n" +
//                "Anh biết cô sẽ không như thế, vì anh hiểu cô còn hơn cả bản thân mình.\n" +
//                "\n" +
//                "“Tiểu thư, chỉ có một mình à? Có muốn đi uống cốc trà cùng ta không?”\n" +
//                "\n" +
//                "Anh tựa vào thân cây, ngẩng đầu nhìn lên, quả nhiên giữa đám cành lá rậm rạp có một dáng người nhỏ nhắn đang ngồi.\n" +
//                "\n" +
//                "Rõ ràng anh là người có tính khí thận trọng nhưng lại học giọng điệu nói năng tùy tiện của giới trẻ. Nếu là trước kia, cô nhất định sẽ bị anh chọc cho bật cười, nhưng bây giờ, cô chẳng có tâm trạng đâu mà xem anh đùa.\n" +
//                "“Xin lỗi, là anh không tốt, không nghĩ đến tâm trạng của em.” Anh xoa nhẹ mái tóc ngắn chưa tới vai của cô, khẽ hỏi: “Tình, em muốn anh làm thế nào?” Phải làm thế nào thì cô mới vui lên?\n" +
//                "\n" +
//                "“Anh cho rằng em đang ghen tỵ với anh ư?” Cô lên tiếng, nhảy dựng lên như bị sỉ nhục.\n" +
//                "\n" +
//                "“Anh không có ý đó.” Là mắt xích nào có vấn đề? Anh dùng từ không đúng ư? Tại sao lại khiến cô có cảm giác này?\n" +
//                "\n" +
//                "Cô tức tối, ra sức hất cánh tay vỗ về của anh. “Thẩm Hàn Vũ, anh là đồ… ngu ngốc nhất thế kỷ, ngu ngốc không ai bằng! Em… em sắp bị anh làm cho tức chết rồi!”\n" +
//                "\n" +
//                "Thẩm Hàn Vũ trợn tròn mắt, ngẩn người nhìn bóng dáng cô đang chạy như bay xa dần, chưa kịp hoàn hồn.\n" +
//                "\n" +
//                "Không phải như vậy ư? Vậy rốt cuộc vấn đề là ở đâu?\n" +
//                "\n" +
//                "Anh như rơi vào đám sương mù, một hồi sau mới phát hiện trái tim con gái quả là khó hiểu.\n" +
//                "\n" +
//                "Nghi ngờ này cứ quấy rầy anh mãi, không tìm ra đáp án, tối nay anh mất ngủ mất thôi.\n" +
//                "\n" +
//                "Cả buổi tối lật đi lật lại trên giường, giấc ngủ mãi chưa tới, anh mở mắt, nhìn chăm chú sang chỗ trống bên cạnh, thở dài.\n" +
//                "\n" +
//                "Hồi nhỏ, hoàn cảnh gia đình không được khá giả, anh và Tình ở chung một phòng, ngủ cùng một giường, trong đêm đông lạnh lẽo, cơ thể bé nhỏ của Tình vô cùng ấm áp.\n" +
//                "\n" +
//                "Sau này, cuộc sống cải thiện hơn, khi đó cô vừa lên lớp một, cha mẹ cho rằng hai đứa lớn rồi, không thích hợp để ngủ chung nữa, sau khi suy nghĩ mội hồi, bèn quyết định cơi nới thêm một phòng.\n" +
//                "\n" +
//                "Hai đứa đều có phòng riêng nhưng Tình không quen, mỗi lần mất ngủ đều ôm gối chạy sang gõ cửa phòng anh, nói: “Em đã quen có anh ở bên cạnh, nửa đêm tỉnh dậy, đột nhiên không thấy anh đâu, chỉ còn mình em, em đương nhiên sẽ sợ hãi!”\n" +
//                "\n" +
//                "Cứ như vậy, cha mẹ cũng không tách họ ra nữa, để mặc cô ở lỳ phòng anh thêm một năm. Sau khi lên lớp hai, cô mới từ từ chấp nhận sự thật rằng cô phải ngủ một mình, không còn hơi một tí là ôm gối tìm anh.\n" +
//                "\n" +
//                "Nhưng thi thoảng bốc đồng, cô lại mang theo nụ cười ngọt ngào, xuất hiện trước cửa phòng anh, nũng nịu hói: “Anh, tối nay ngủ với anh được không?”\n" +
//                "\n" +
//                "Nghĩ tới sự bất thường của cô, Thẩm Hàn Vũ ngồi dậy, nhìn chằm chằm vào bức tường trắng.\n" +
//                "\n" +
//                "Tình hiếm khi khó chịu với anh như vậy, rốt cuộc là có chuyện gì?\n" +
//                "\n" +
//                "Anh cố gắng nhớ lại, lần bất thường trước hình như là năm cô mười ba tuổi, khi kỳ kinh nguyệt đầu tiên xuất hiện, cô cả ngày kỳ quặc, không còn hở ra là ườn trên người anh nữa.\n" +
//                "\n" +
//                "Anh còn tưởng mình có chỗ nào đắc tội với cô, mất một lúc lâu mới hiểu là cô bé lớn rồi, đã biết xấu hổ.\n" +
//                "\n" +
//                "Hồi đó, mỗi lần thấy anh, cô đều lúng túng, không biết phải nói gì, đành thẹn thùng quay người chạy đi.\n" +
//                "\n" +
//                "Vậy còn bây giờ? Không lẽ là thời kỳ mãn kinh? Em gái mới mười lăm tuổi mà!\n" +
//                "\n" +
//                "Anh cảm thấy buồn cười với suy nghĩ của mình, cứ phán đoán lung tung như vậy, sớm muộn gì anh cũng thần kinh mất thôi!\n" +
//                "\n" +
//                "Anh tung chăn, tới phòng bên cạnh, gỗ nhẹ hai cái: “Em gái, ngủ rồi à?”\n" +
//                "\n" +
//                "Im ắng một lúc, không có tiếng đáp lại.\n" +
//                "\n" +
//                "Anh xoay nắm đấm cửa, chắc chắn rằng cô không đá chăn đi mới nhìn lên bàn, thấy bát cơm anh phần cô đã được ăn hết, anh bèn thu dọn cái bát rỗng, rồi nhẹ nhàng đóng cửa.\n" +
//                "\n" +
//                "Lúc anh rửa bát, cha cũng vào bếp rót nước.\n" +
//                "\n" +
//                "“Tiểu Tình ngủ rồi à?”\n" +
//                "\n" +
//                "“Vâng.”\n" +
//                "\n" +
//                "“Tình cảm hai anh em con vẫn tốt chứ?”\n" +
//                "\n" +
//                "Bàn tay đang rửa bát hơi khựng lại: “… Vâng.”\n" +
//                "\n" +
//                "“Từ nhỏ, con bé này chẳng quấn ai, chỉ quấn con. Mỗi lần nó khóc, chỉ có con mới dỗ dành nổi. Nó chỉ nghe lời con, chịu uất ức cũng tìm anh trai khóc lóc, kể lể. Cha thấy rõ, nó rất ỷ lại vào con, coi trọng con hơn bất cứ người nào.”\n" +
//                "\n" +
//                "“Cha?” Anh lạ lùng nhìn cha, không rõ vì sao cha đột nhiên nhắc tới chuyện này.\n" +
//                "\n" +
//                "“Không có gì, cha chỉ muốn con nhớ một điều, con bé là em gái duy nhất của con, con là người quan trọng nhất với nó trên thế gian này, con phải có trách nhiệm với con bé.”\n" +
//                "\n" +
//                "“Con biết.”\n" +
//                "\n" +
//                "“Vậy con phải hứa với cha, cả đời này sẽ không vứt bỏ, lơ là nó, bất cứ lúc nào cũng phải bảo vệ, chăm sóc nó.”\n" +
//                "\n" +
//                "Biết rằng cha không chỉ đang nói chuyện phiếm, thái độ của anh trở nên thận trọng hơn, nghiêm túc trả lời từ chính con tim mình: “Vâng, thưa cha.”\n" +
//                "\n" +
//                "“Được, vậy cha giao Tiểu Tình cho con, đừng để cha thất vọng!”\n" +
//                "\n" +
//                "Thẩm Hàn Vũ khóa vòi nước, giật mình quay người.\n" +
//                "\n" +
//                "Đây… là sự phó thác ư?\n" +
//                "\n" +
//                "Thân thế của Tình từ lâu đã là một bí mật công khai giữa anh và cô, chỉ có điều không ai nói thẳng ra. Đối với anh, cho dù có huyết thống hay không, cô vẫn là đứa em gái anh yêu thương nhất. Điều này chẳng ảnh hưởng chút nào đến vị trí của cô trong trái tim anh và trong gia đình này.\n" +
//                "\n" +
//                "Còn cha? Cha phát hiện hai đứa đã biết rõ chuyện này từ khi nào? Thậm chí còn có ý giao phó cả đời Tình cho anh?\n" +
//                "\n" +
//                "Vì sao lúc này ai cũng đều kỳ lạ vậy?\n" +
//                "\n" +
//                "“Tình!\n" +
//                "\n" +
//                "Tan học đợi anh, anh tới đón em, có chuyện muốn nói.\n" +
//                "\n" +
//                "Ký tên: Anh trai.”\n" +
//                "\n" +
//                "Tối qua, anh để lại dòng chữ này cho cô, sáng nay cô đã ra ngoài trước anh một bước, nhưng khi đến phòng cô, nhìn thấy mảnh giấy bị vo tròn, anh biết cô đã đọc.\n" +
//                "\n" +
//                "Sau khi tan học, anh đến trường cô, cũng là trường cũ mà anh đã tốt nghiệp ba năm trước, đợi cô cả buổi, mãi mà chẳng thấy.\n" +
//                "\n" +
//                "Thấy giáo viên, học sinh cả trường đã về gần hết, anh bắt đầu lo lắng, không lẽ cô lại gây ra chuyện gì khiến thầy cô giáo phạt, bắt ở lại trường ư?\n" +
//                "\n" +
//                "Sau đó, vài nữ sinh chạy tới gọi anh là “học trưởng[1]“, tự giới thiệu là bạn học của Tình, quấn lấy anh nói chuyện.\n" +
//                "\n" +
//                "[1] Đàn anh lớp trên.\n" +
//                "\n" +
//                "Anh từng là nhân vật quan trọng của trường này, nổi tiếng là một học sinh giỏi toàn diện, hoàn hảo cả về năm mặt: thể chất, trí tuệ, phẩm hạnh, thẩm mỹ và tinh thần tập thể, tài hoa phong nhã trời sinh, trên bục trao học bổng không bao giờ thiếu bóng dáng anh. Cho tới bây giờ, đã ba năm trôi qua, rất nhiều thầy cô, học sinh vẫn còn bàn tán xôn xao. Năm đó Tình vừa mới vào học, vì thân phận “em gái tài tử của trường – Thẩm Hàn Vũ” mà thu hút không ít ánh nhìn.\n" +
//                "\n" +
//                "Ba năm trước, do thành tích đứng đầu bảng toàn huyện, anh thi đỗ vào trường trung học thành phố, khiến cho ngôi trường vô danh của thị trấn nhỏ này có thêm không ít ánh hào quang. Chẳng trách ngày hôm nay, cái tên Thẩm Hàn Vũ vẫn còn vang dội khắp trường.\n" +
//                "\n" +
//                "Cũng vì quá rõ mối quan hệ của họ nên không ít người nói những lời như: “Cái gì? Anh chàng Thẩm Hàn Vũ đẹp trai ưu tú đó là anh của cậu ư? Hai anh em cậu chẳng giống nhau chút nào cả…” Anh lo lắng những lời nói vô ý vô tứ đó sẽ làm tổn thương lòng tự tôn của cô bé.\n" +
//                "\n" +
//                "Bạn bè của Thiên Tình nói cô đã rời trường từ lâu, anh vô tâm bỏ lại sự mơ mộng của các cô thiếu nữ này, vội vàng về nhà.\n" +
//                "\n" +
//                "Quả nhiên, Tình đã về, đang yên lặng ngồi học từ vựng tiếng Anh.\n" +
//                "\n" +
//                "“Hàn Vũ, sao hôm nay con về muộn thế? Chẳng phải nói là đi đón Tiểu Tình sao? Tiểu Tình về từ lâu rồi mà.”\n" +
//                "\n" +
//                "Anh quay đầu, nhìn thẳng vào Tình. “Dạ… Con bận nói chút chuyện vói thầy giáo nên bị trễ, sợ Tình đợi lâu nên để em về trước.”\n" +
//                "\n" +
//                "“Vậy à?” Mẹ gật đầu rồi đi vào bếp.\n" +
//                "\n" +
//                "Thấy mẹ đã đi xa, anh tới trước mặt cô, khẽ hỏi: “Sao không đợi anh?”\n" +
//                "\n" +
//                "“Em vốn dĩ đâu có đồng ý.”\n" +
//                "\n" +
//                "“Tình, em ngẩng đầu lên, chúng ta nói chuyện!”\n" +
//                "\n" +
//                "“Ngày mai em có bài kiểm tra tiếng Anh.” Cô vẫn cố chấp nhìn chăm chú vào cuốn sách.\n" +
//                "\n" +
//                "“Em chăm chỉ tới mức không có cả thời gian nói chuyện với anh từ bao giờ vậy?”\n" +
//                "\n" +
//                "“Bây giờ.”\n" +
//                "\n" +
//                "Thẩm Hàn Vũ hít một hơi: “Ngẩng đầu lên đi, có gì bất mãn thì hãy nói thẳng với anh, anh không chấp nhận kiểu chiến tranh lạnh trẻ con này đâu.”\n" +
//                "\n" +
//                "“Không có.”\n" +
//                "\n" +
//                "“Anh nói ngẩng đầu lên!” Giọng nói có phần hơi kích động của anh lập tức thu hút ánh mắt cha đang ngồi đọc báo ở xa.\n" +
//                "\n" +
//                "“Sao thế? Hàn Vũ?”\n" +
//                "\n" +
//                "“Xin lỗi cha, chúng con không sao ạ.” Anh giơ tay kéo cô vào phòng, đóng cửa lại. “Hai ngày nay em làm sao thế? Thẩm Thiên Tình mà anh biết không bao giờ vô cớ gây sự thế này, rốt cuộc là thế nào vậy?”\n" +
//                "\n" +
//                "Thẩm Thiên Tình định nói gì đó, lặng lẽ ngước mắt lên, thấy bức thư thoang thoảng mùi thơm trong tay anh, cô cắn môi, tức giận không nói nữa.\n" +
//                "Nương theo ánh mắt cô, anh giơ bức thư viết tên mình lên: “Còn nữa, chuyện thư từ này là thế nào? Nghe nói có không ít thư gửi cho anh nhưng nửa lá anh cũng không nhìn thấy, vì chiếu cố đến thể diện của em, anh mới không vạch trần chuyện này trước mặt bạn em, nhưng anh nghĩ em nợ anh một lời giải thích.”\n" +
//                "“Anh quan tâm ư? Có biết bao nữ sinh ngưỡng mộ, viết thư tình cho anh, điều này làm thỏa mãn thói hư vinh của anh, đúng không?” Anh trai coi trọng thư tình của những nữ sinh không biết tên hơn cả cô, khiến cô cảm thấy bị tổn thương, trong lòng chua chát tựa như bị vô số mũi kim nhỏ châm vào…\n" +
//                "“Vấn đề không phải là quan tâm hay không, mà là chúng liên quan tới anh, em có nghĩa vụ báo cho anh biết, còn quan tâm hay không, đó là việc của anh.”\n" +
//                "“Được, em thừa nhận mình đã giấu thư đi, vậy thì sao?”\n" +
//                "\n" +
//                "“Đem ra đây!”\n" +
//                "\n" +
//                "“Không!”\n" +
//                "\n" +
//                "“Anh nói em đem ra đây!“\n" +
//                "“Không, không, không!” Cô bướng bỉnh phản ứng lại, vênh mặt trợn mắt nhìn anh, không chút sợ hãi.\n" +
//                "“Thẩm Thiên Tinh, em đừng làm anh tức giận.”\n" +
//                "“Anh tức cũng chẳng có tác dụng gì, những bức thư đó em đều xé hết, đốt hết, vứt hết rồi, một bức cũng chẳng còn, chắc là anh tiếc lắm nhỉ? Tất cả anh đều không được nhìn thấy nữa đâu, trong đó còn có cả thư của hoa khôi lớp, hoa khôi trường, ai cũng xinh đẹp vô cùng. Anh mắng em đi, đánh em đi! Dù sao những bức thư đó cũng quan trọng hơn em, vì chúng mà anh trở nên hung dữ với em…”\n" +
//                "\n" +
//                "Thẩm Hàn Vũ nhíu mày: “Anh tùy việc mà xét. Nếu em không đồng ý, có thể từ chối. Nhận sự giao phó của người ta thì phải làm cho trọn. Đây không phải là thái độ đối nhân xử thế đúng mực, anh cực kỳ không thích hành vi này của em. »\n" +
//                "\n" +
//                "Anh nói anh không thích cô, vậy là bây giờ anh không thích cô nữa rồi…\n" +
//                "\n" +
//                "Giọt lệ tủi thân rơm rớm trên viền mi, cô tức giận chạy ra khỏi phòng, chẳng mấy chốc đã quay lại, ném cả tập thư vào người anh: “Cầm lấy, anh thích thì cứ giữ đi, đừng giơ cái bộ mặt đòi nợ ra nữa, ai thèm chứ!”\n" +
//                "\n" +
//                "Thẩm Hàn Vũ sững người, từng bức, từng bức lả tả như tuyết rơi, khi ngẩng đầu lên, cô đã mất hút khỏi tầm mắt.\n" +
//                "\n" +
//                "Buối tối, Thiên Tĩnh không ra ăn cơm, mẹ quan tâm vào phòng một chuyến, cô nói không hứng thú, không muốn ăn.\n" +
//                "\n" +
//                "Mẹ ít nhiều cũng nhìn ra giữa hai đứa đang có mâu thuẫn, bèn khuyên anh: “Tính Tiểu Tình là vậy, con làm anh thì nhường nó chút đi, đừng chấp nó nữa!”\n" +
//                "\n" +
//                "“Mẹ…” Anh không nói nữa.\n" +
//                "\n" +
//                "Mẹ cười: “Nó cũng chẳng cố ý chọc giận con đâu, nhất cứ nhất động của con đều có ảnh hưởng rất lớn đến nó, nếu con không tha thứ cho nó, e là nó sẽ tuyệt thực đến chết đói đấy!”\n" +
//                "\n" +
//                "Vấn đề là cô có cần sự tha thứ của anh hay không?\n" +
//                "\n" +
//                "Thẩm Hàn Vũ gắp mấy món ăn cô thích, mang vào phòng.\n" +
//                "\n" +
//                "Bên trong tối om, anh bật đèn, thấy cô nằm trên giường. Cô vội quay lưng, kéo chăn kín đầu, không nhìn anh.\n" +
//                "\n" +
//                "Anh đặt cơm tối lên bàn, ngồi xuống cạnh giường: “Vẫn không vui vì những lời anh nói à?”\n" +
//                "\n" +
//                "“…” Trong chăn im lặng một hồi.\n" +
//                "\n" +
//                "Anh lại mở miệng: “Thực sự giận anh đến thế sao? Giận tới mức muốn tuyệt thực phản đối?”\n" +
//                "\n" +
//                "“…” Vẫn không nói năng gì.\n" +
//                "\n" +
//                "“Không được như thế nữa, Tình, quay mặt lại đây cho anh!” Anh dùng tay kéo chăn, lật người cô, bỗng nhiên phát hiện mặt cô đầy nước mắt, gối ướt đẫm một mảng.'," +
//                "7)");



//        db.QueryData("DELETE FROM Truyen");
//        db.QueryData("INSERT INTO Truyen VALUES(null," +
//                "'Thất Tịch Không Mưa'," +
//                "'https://newshop.vn/public/uploads/products/10221/that-tich-khong-mua-bia.jpg'," +
//                "'Lâu Vũ Tình'," +
//                "'6 Chương'," +
//                "2," +
//                "'Ngôn tình ngược'," +
//                "'SE'," +
//                "'Nhân vật nữ chính Thẩm Thiên Tình sinh ngày Mùng 7 tháng 7." +
//                "Từ nhỏ cô đã thầm yêu anh, như số kiếp không thể thay đổi" +
//                "Tình  yêu trong sáng ấy, như lần đầu được nếm mùi vị của quả khế mới chín." +
//                "Sau đó cô và anh xa nhau, gặp lại đều cách nhau ba năm." +
//                "15 tuổi, anh lên phía bắc học, từ  đó mất liên lạc" +
//                "18 tuổi, cô nông nổi đi gặp anh, đổi lại là sự đau lòng" +
//                "21 tuổi, cuối cùng anh cũng quay về để chịu tang mẹ;24 tuổi, anh kết hôn, đưa người vợ mới cưới tới tận nơi xa." +
//                "Anh từng là thần hộ mệnh của cô, dịu dàng, cẩn thận che chở, bao dung." +
//                "Đã từng ngoắc tay với cô, thề sẽ mãi mãi ở bên nhau." +
//                "Cô có thể mất đi tất cả, nhưng không thể không có anh – người hiểu cô nhất." +
//                "Ngày 7-7 là ngày gặp mặt của Ngưu Lang Chức Nữ," +
//                "mưa ngày 7-7 là nước mắt của nỗi nhớ nhung" +
//                "Vậy, cô 27 tuổi, liệu có thể có một ngày 7-7 không mưa," +
//                "Để cô được gặp lại anh một lần nữa……..')");
//
//        db.QueryData("INSERT INTO Truyen VALUES(null," +
//                "' NGƯỜI SÓI MOWGLI'," +
//                "'https://salt.tikicdn.com/cache/400x400/media/catalog/product/s/c/scan_196.jpg'," +
//                "'Rudyard Kipling'," +
//                "'6 Chương'," +
//                "3," +
//                "'Người sói'," +
//                "''," +
//                "'Tóm tắt: Kipling nổi tiếng trước hết là một nhà thơ. Sau khi Lord Tennyson qua đời vào năm 1892, có thể nói Kipling đã chiếm vị trí số một trong lòng công chúng yêu thơ nước Anh. Ông cũng là người viết truyện ngắn và tiểu thuyết lừng danh. Bạn đọc trẻ khắp năm châu thì yêu quí Kipling qua những tác phẩm viết cho lứa tuổi của họ: Những Thuỷ Thủ Dũng Cảm (1890), Sách Rừng (quyển 1 năm 1894, quyển 2 năm 1895), Kim (1901). Ông được trao giải Nobel văn chương năm 1907.\n" +
//                "Từ năm 1901, Rudyard Kipling định cư ở Anh và qua đời ngày 18 tháng 1 năm 1936 tại London.\n" +
//                "Hai quyển Sách Rừng có thể coi là tập hợp những câu chuyện thú vị, hấp dẫn bậc nhất về đời sống hoang dã trong văn chương nhân loại từ trước tới nay, mà nhân vật chính là một chú bé được bầy sói nuôi và huấn luyện giữa rừng xanh, trở thành chúa tể của rừng. Mowgli - Người Sói là bản dịch tiếng Việt hai quyển Sách Rừng, bám sát bản gốc, chỉ lược bỏ một số bài thơ quá dài chen giữa các câu chuyện. Bản dịch đã được đông đảo bạn đọc tán thưởng qua hai lần in năm 1987, 1988 của Nhà Xuất Bản Trẻ. Bản in lần thứ 3 này đã được dịch giả sửa chữa một số chỗ.')");


//        db.QueryData("INSERT INTO TheLoai VALUES(null," +
//                "'https://i.pinimg.com/originals/69/e9/59/69e959b89dafdad5473b29e9e27139b7.jpg'," +
//                "'Cổ tích')");
//
//        db.QueryData("INSERT INTO TheLoai VALUES(null," +
//                "'https://scr.vn/wp-content/uploads/2020/09/Truyen-ngon-tinh-sieu-sac.jpg'," +
//                "'Ngôn tình')");
//
//        db.QueryData("INSERT INTO TheLoai VALUES(null," +
//                "'https://sohanews.sohacdn.com/zoom/600_315/2019/7/23/death-15638468372881087338521-crop-1563846855105843971154.jpg'," +
//                "'Bí ẩn')");
//
//        db.QueryData("INSERT INTO TheLoai VALUES(null," +
//                "'https://i.vietgiaitri.com/2018/9/4/danh-gia-phantom-doctrine-tuyet-pham-moi-cho-the-loai-diep-vien--13f1fb.jpg'," +
//                "'Hành động')");
//
//        db.QueryData("INSERT INTO TheLoai VALUES(null," +
//                "'https://tiengtrung.com/wp-content/uploads/2020/11/%E1%BA%A2nh-ch%E1%BB%A5p-M%C3%A0n-h%C3%ACnh-2020-11-12-l%C3%BAc-17.21.57.png'," +
//                "'Đam mỹ')");
//
//        db.QueryData("INSERT INTO TheLoai VALUES(null," +
//                "'https://img4.thuthuatphanmem.vn/uploads/2020/04/06/top-anime-kinh-di-hoc-duong-hay-nhat_074104740.png'," +
//                "'Kinh Dị')");
//
//        db.QueryData("INSERT INTO TheLoai VALUES(null," +
//                "'https://vnwriter.net/wp-content/uploads/2018/03/sach-hay-ve-vu-tru-780x405.png'," +
//                "'Viễn tưởng')");
//
//        db.QueryData("INSERT INTO TheLoai VALUES(null," +
//                "'https://e.khoahoc.tv/photos/image/2016/05/20/trang-xanh.jpg'," +
//                "'Truyện ngắn')");
//
//        db.QueryData("INSERT INTO TheLoai VALUES(null," +
//                "'https://i.pinimg.com/474x/fe/c8/42/fec84229bca9c92d33e2f9b1170096ed.jpg'," +
//                "'Phiêu lưu')");
//
//        db.QueryData("INSERT INTO TheLoai VALUES(null," +
//                "'https://khoahoctamlinh.vn/img/news/2021/07/larger/2729-chua-lanh-ket-noi-voi-higher-self---vi-thay-tam-linh-ben-trong-1.jpg'," +
//                "'Tâm linh')");
//
//        db.QueryData("INSERT INTO TheLoai VALUES(null," +
//                "'https://top.trangdangtin.com/htdocs/images/news/2020/01/10/800/quynh_aka.png'," +
//                "'Hài hước')");
//
//        db.QueryData("INSERT INTO TheLoai VALUES(null," +
//                "'https://i.pinimg.com/550x/87/68/ef/8768ef74668f66d1580e7a4ce4ef97db.jpg'," +
//                "'Ma cà rồng')");
//
//        db.QueryData("INSERT INTO TheLoai VALUES(null," +
//                "'https://i.pinimg.com/originals/69/e9/59/69e959b89dafdad5473b29e9e27139b7.jpg'," +
//                "'Siêu nhiên')");
        gridView=(GridView) view.findViewById(R.id.gridViewHinhAnh);

        list= new ArrayList<>();
        list.clear();
//        TheLoai theLoai= new TheLoai();
//        theLoai.ten="Cổ tích";
//        theLoai.hinhanh="https://i.pinimg.com/originals/69/e9/59/69e959b89dafdad5473b29e9e27139b7.jpg";
//        list.add(theLoai);
//
//        theLoai= new TheLoai();
//        theLoai.ten="Ngôn tình";
//        theLoai.hinhanh="https://scr.vn/wp-content/uploads/2020/09/Truyen-ngon-tinh-sieu-sac.jpg";
//        list.add(theLoai);
//
//        theLoai= new TheLoai();
//        theLoai.ten="Bí ẩn";
//        theLoai.hinhanh="https://sohanews.sohacdn.com/zoom/600_315/2019/7/23/death-15638468372881087338521-crop-1563846855105843971154.jpg";
//        list.add(theLoai);
//
//        theLoai= new TheLoai();
//        theLoai.ten="Hành động";
//        theLoai.hinhanh="https://i.vietgiaitri.com/2018/9/4/danh-gia-phantom-doctrine-tuyet-pham-moi-cho-the-loai-diep-vien--13f1fb.jpg";
//        list.add(theLoai);

        gridView=(GridView) view.findViewById(R.id.gridViewHinhAnh);

        list= new ArrayList<>();
        list.clear();

//        addDataToFirestore("1","https://i.pinimg.com/originals/69/e9/59/69e959b89dafdad5473b29e9e27139b7.jpg","Cổ Tích");
//        addDataToFirestore("2","https://scr.vn/wp-content/uploads/2020/09/Truyen-ngon-tinh-sieu-sac.jpg","Ngôn Tình");
//        addDataToFirestore("3","https://sohanews.sohacdn.com/zoom/600_315/2019/7/23/death-15638468372881087338521-crop-1563846855105843971154.jpg","Bí Ẩn");
//        addDataToFirestore("4","https://i.vietgiaitri.com/2018/9/4/danh-gia-phantom-doctrine-tuyet-pham-moi-cho-the-loai-diep-vien--13f1fb.jpg","Hành Động");
//        addDataToFirestore("5","https://tiengtrung.com/wp-content/uploads/2020/11/%E1%BA%A2nh-ch%E1%BB%A5p-M%C3%A0n-h%C3%ACnh-2020-11-12-l%C3%BAc-17.21.57.png","Đam Mỹ");
//        addDataToFirestore("6","https://img4.thuthuatphanmem.vn/uploads/2020/04/06/top-anime-kinh-di-hoc-duong-hay-nhat_074104740.png","Kinh Dị");
//        addDataToFirestore("7","https://vnwriter.net/wp-content/uploads/2018/03/sach-hay-ve-vu-tru-780x405.png","Viễn Tưởng");
//        addDataToFirestore("8","https://e.khoahoc.tv/photos/image/2016/05/20/trang-xanh.jpg","Truyện Ngắn");
//        addDataToFirestore("9","https://i.pinimg.com/474x/fe/c8/42/fec84229bca9c92d33e2f9b1170096ed.jpg","Phiêu Lưu");
//        addDataToFirestore("10","https://khoahoctamlinh.vn/img/news/2021/07/larger/2729-chua-lanh-ket-noi-voi-higher-self---vi-thay-tam-linh-ben-trong-1.jpg","Tâm Linh");
//        addDataToFirestore("11","https://top.trangdangtin.com/htdocs/images/news/2020/01/10/800/quynh_aka.png","Hài Hước");
//        addDataToFirestore("12","https://i.pinimg.com/550x/87/68/ef/8768ef74668f66d1580e7a4ce4ef97db.jpg","Ma Cà Rồng");
//        addDataToFirestore("13","https://i.pinimg.com/originals/69/e9/59/69e959b89dafdad5473b29e9e27139b7.jpg","Siêu Nhiên");

        getTheLoais();


       return view;
    }
//        private  void  addDataToFirestore(String id, String hinhanh,String title){
//        FirebaseFirestore dataBase= FirebaseFirestore.getInstance();
//        HashMap<String,Object> data= new HashMap<>();
//        data.put("ID",id);
//        data.put("hinhanh",hinhanh);
//        data.put("title",title);
//        dataBase.collection("theloai").add(data).addOnSuccessListener(documentReference -> {
//            Toast.makeText(view.getContext(), "Data Inserted", Toast.LENGTH_SHORT).show();
//        }).addOnFailureListener(exception->{
//            Toast.makeText(view.getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
//        });
//    }

    public  void getTheLoais(){

        FirebaseFirestore database= FirebaseFirestore.getInstance();
        database.collection("theloai")
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful() && task.getResult()!=null){
                        List<TheLoai> users= new ArrayList<>();
                        for(QueryDocumentSnapshot documentSnapshot: task.getResult()){
                            TheLoai user= new TheLoai();
                            user.id=documentSnapshot.getId();
                            user.hinhanh=documentSnapshot.getString(DBTheLoai.hinhanh);
                            user.ten=documentSnapshot.getString(DBTheLoai.title);
                            users.add(user);
                        }
                        if(users.size()>0){
                            theLoaiAdapter= new TheLoaiAdapter(view.getContext(),R.layout.layout_table_image,users);
                            gridView.setAdapter(theLoaiAdapter);
                        }else{

                        }
                    }else{

                    }
                });
    }

}
