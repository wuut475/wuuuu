package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.*;

public class Update_Info {

    public Update_Info(int s, Stage stage){

        DBUtil dbUtil = new DBUtil();
        Connection conn = dbUtil.getConn();
        Stage stage1 = new Stage();

        stage1.setTitle("修改信息");
        stage1.setWidth(400);
        stage1.setHeight(570);
        stage1.setX(740);
        stage1.setY(180);
        stage1.setResizable(false);
        stage1.initOwner(stage);
        stage1.initModality(Modality.WINDOW_MODAL);
        stage1.show();  //  让窗口显示出来
        AnchorPane an = new AnchorPane();
        Scene scene = new Scene(an);
        stage1.setScene(scene); // 设置场景
        Text t1 = new Text( 10,70,"名称");
        t1.setFont(new Font(20));
        Text t2 = new Text( 10,120,"颜色");
        t2.setFont(new Font(20));
        Text t3 = new Text( 10,170,"尺寸");
        t3.setFont(new Font(20));
        Text t4 = new Text( 10,220,"价格");
        t4.setFont(new Font(20));
        Text t5 = new Text( 10,270,"订单数量");
        t5.setFont(new Font(20));
        Text t6 = new Text( 10,320,"生产进度");
        t6.setFont(new Font(20));
        Text t7 = new Text( 10,370,"原材料信息");
        t7.setFont(new Font(20));
        Text t8 = new Text( 10,420,"质量情况");
        t8.setFont(new Font(20));


        TextField a1 = new TextField();
        AnchorPane.setLeftAnchor(a1,118.0);
        AnchorPane.setTopAnchor(a1,48.0);
        TextField a2 = new TextField();
        AnchorPane.setLeftAnchor(a2,118.0);
        AnchorPane.setTopAnchor(a2,98.0);
        TextField a3 = new TextField();
        AnchorPane.setLeftAnchor(a3,118.0);
        AnchorPane.setTopAnchor(a3,148.0);

        ComboBox<String> a4 = new ComboBox<>();
        a4.getItems().addAll("s","l","xl","2xl","3xl");
        a4.setValue("xl");
        AnchorPane.setLeftAnchor(a4,118.0);
        AnchorPane.setTopAnchor(a4,198.0);

        TextField a5 = new TextField();
        AnchorPane.setLeftAnchor(a5,118.0);
        AnchorPane.setTopAnchor(a5,248.0);

        TextField a6 = new TextField();
        AnchorPane.setLeftAnchor(a6,118.0);
        AnchorPane.setTopAnchor(a6,298.0);
        TextField a7 = new TextField();
        AnchorPane.setLeftAnchor(a7,118.0);
        AnchorPane.setTopAnchor(a7,348.0);

        ComboBox<String> a8 = new ComboBox<>();
        a8.getItems().addAll("不合格","优秀");
        a8.setValue("优秀");
        AnchorPane.setLeftAnchor(a8,118.0);
        AnchorPane.setTopAnchor(a8,398.0);

        Button b1 = new Button("立即修改");
        AnchorPane.setTopAnchor(b1,478.0);
        AnchorPane.setLeftAnchor(b1,136.0);


        b1.setStyle(
                "-fx-font-size: 23px"
        );

        an.getChildren().addAll(t1,t2,t3,t4,t5,t6,t7,a6,a7,a1,a2,a3,a4,a5,b1,t8,a8);
        String sql = "select * from wool_sweater";
        String s1="",s2="",s3="",s4="",s5="",s6="",s7="",s8="";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                s1 = resultSet.getString(2);
                s2 = resultSet.getString(3);
                s3 = resultSet.getString(4);
                s4 = resultSet.getString(5);
                s5 = resultSet.getString(6);
                s6 = resultSet.getString(7);
                s7 = resultSet.getString(8);
                s8 = resultSet.getString(9);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        a1.setText(s1);
        a2.setText(s2);
        a3.setText(s3);
        a4.setValue(s4);
        a5.setText(s5);
        a6.setText(s6);
        a7.setText(s7);
        a8.setValue(s8);
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String sql1 = "update wool_sweater set name = ?,color=?,size = ?,price=?,order_quantity=?,production_progress=?,material_info=?,quality_status=? where id = ?";
                try {

                    PreparedStatement preparedStatement = conn.prepareStatement(sql1);
                    preparedStatement.setString(1,a1.getText());
                    preparedStatement.setString(2,a2.getText());
                    preparedStatement.setString(3,a3.getText());
                    preparedStatement.setString(4,a4.getValue());
                    preparedStatement.setString(5,a5.getText());
                    preparedStatement.setString(6,a6.getText());
                    preparedStatement.setString(7,a7.getText());
                    preparedStatement.setString(8,a8.getValue());
                    preparedStatement.setInt(9,s);
                    preparedStatement.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                stage1.close();
                stage.close();
                Wool_Info woolInfo = new Wool_Info();
            }
        });
    }
}
