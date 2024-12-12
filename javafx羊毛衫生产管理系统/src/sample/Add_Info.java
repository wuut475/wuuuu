package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.sql.*;

public class Add_Info {

    public Add_Info(Stage stage) {

        DBUtil dbUtil = new DBUtil();
        Connection conn = dbUtil.getConn();
        Stage stage1 = new Stage();
        stage1.setTitle("新增信息");
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


        Button b1 = new Button("添加信息");
        AnchorPane.setTopAnchor(b1,478.0);
        AnchorPane.setLeftAnchor(b1,136.0);


        b1.setStyle(
                "-fx-font-size: 23px"
        );

        an.getChildren().addAll(t1,t2,t3,t4,t5,t6,t7,a7,a6,a1,a2,a3,a4,a5,b1,t8,a8);


        // 这个是 点击 增加信息按钮
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    if (a1.getText().isEmpty()||a2.getText().isEmpty()||a3.getText().isEmpty()){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText(null);
                        alert.setContentText("请输入完整的信息!");
                        alert.setResizable(false);
                        alert.showAndWait();
                    }else {
                        String sql= "insert into wool_sweater (name,color,size , price , order_quantity,production_progress,material_info,quality_status) values (?,?,?,?,?,?,?,?);";
                        PreparedStatement pst = null;
                        try {
                            pst = conn.prepareStatement(sql);
                            pst.setString(1,a1.getText());
                            pst.setString(2,a2.getText());
                            pst.setString(3,a3.getText());
                            pst.setString(4,a4.getValue());
                            pst.setString(5,a5.getText());
                            pst.setString(6,a6.getText());
                            pst.setString(7,a7.getText());
                            pst.setString(8,a8.getValue());
                            pst.executeUpdate();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }

                        stage1.close();
                        stage.close();
                        Wool_Info woolInfo = new Wool_Info();

                    }

            }
        });
    }


}
