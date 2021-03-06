
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;


/**
 * Constructor method
 * Takes in a scene, a stage and a controller
 */
public class TerrainCreatorView {
    Scene scene;
    Stage stage;
    Controller controller;

    /**
     * @param controller
     */
    public TerrainCreatorView(Controller controller) {
        this.controller = controller;
    }

    /**
     * Creates the actual window that pops up and allows for terrain creator. No parameters or returns
     */
    public void createWindow() {
        stage = new Stage();
        stage.setTitle("Create Terrain");
        VBox terrain = new VBox();
        terrain.setAlignment(Pos.BASELINE_CENTER);
        terrain.setSpacing(15);
        terrain.setPadding(new Insets(10,10,10,10));

        Label nameLabel = new Label("Terrain Name:");
        TextField terrainName = new TextField();
        terrainName.setId("terrainName");
        terrainName.setPromptText("Terrain Name");
        terrainName.setMaxWidth(300);
        terrainName.setAlignment(Pos.BASELINE_CENTER);
        HBox nameBox = new HBox(5);
        nameBox.getChildren().addAll(nameLabel,terrainName);
        nameBox.setAlignment(Pos.BASELINE_CENTER);

        CheckBox hasEventOnEnterCheckBox = new CheckBox();
        hasEventOnEnterCheckBox.setId("hasEventOnEnterCheckBox");
        Label hasEventOnEnterLabel = new Label("Has event on entering space?");
        CheckBox hasEventOnStartTurnCheckBox = new CheckBox();
        hasEventOnStartTurnCheckBox.setId("hasEventOnStartTurnCheckBox");
        Label hasEventOnStartTurnLabel = new Label("Has event on starting a turn in the space?");
        CheckBox hasEventOnEndTurnCheckBox = new CheckBox();
        hasEventOnEndTurnCheckBox.setId("hasEventOnEndTurnCheckBox");
        Label hasEventOnEndTurnLabel = new Label("Has event on ending a turn in the space?");
        HBox eventsBox = new HBox(5);
        eventsBox.getChildren().addAll(hasEventOnEnterLabel,
                hasEventOnEnterCheckBox,
                hasEventOnStartTurnLabel,
                hasEventOnStartTurnCheckBox,
                hasEventOnEndTurnLabel,
                hasEventOnEndTurnCheckBox
                );
        eventsBox.setAlignment(Pos.BASELINE_CENTER);

        Label moveTypeHeader = new Label("This terrain is passable for: ");
        CheckBox isPassableForWalkSpeed = new CheckBox();
        isPassableForWalkSpeed.setId("isPassableForWalkSpeed");
        Label isPassableForWalkSpeedLabel = new Label("Walk Speeds");
        CheckBox isPassableForSwimSpeed = new CheckBox();
        isPassableForSwimSpeed.setId("isPassableForSwimSpeed");
        Label isPassableForSwimSpeedLabel = new Label("Swim Speeds");
        CheckBox isPassableForClimbSpeed = new CheckBox();
        isPassableForClimbSpeed.setId("isPassableForClimbSpeed");
        Label isPassableForClimbSpeedLabel = new Label("Climb Speeds");
        CheckBox isPassableForBurrowSpeed = new CheckBox();
        isPassableForBurrowSpeed.setId("isPassableForBurrowSpeed");
        Label isPassableForBurrowSpeedLabel = new Label("Burrow Speeds");
        CheckBox isPassableForFlySpeed = new CheckBox();
        isPassableForFlySpeed.setId("isPassableForFlySpeed");
        Label isPassableForFlySpeedLabel = new Label("Fly Speeds");
        HBox passableBox = new HBox(5);
        passableBox.getChildren().addAll(
                moveTypeHeader,
                isPassableForWalkSpeedLabel,
                isPassableForWalkSpeed,
                isPassableForSwimSpeedLabel,
                isPassableForSwimSpeed,
                isPassableForClimbSpeedLabel,
                isPassableForClimbSpeed,
                isPassableForBurrowSpeedLabel,
                isPassableForBurrowSpeed,
                isPassableForFlySpeedLabel,
                isPassableForFlySpeed
        );
        passableBox.setAlignment(Pos.BASELINE_CENTER);

        TextField walkSpeedCost = new TextField();
        walkSpeedCost.setId("walkSpeedCost");
        walkSpeedCost.setPromptText("Cost for Walking Speed");
        TextField swimSpeedCost = new TextField();
        swimSpeedCost.setId("swimSpeedCost");
        swimSpeedCost.setPromptText("Cost for Swim Speed");
        TextField climbSpeedCost = new TextField();
        climbSpeedCost.setId("climbSpeedCost");
        climbSpeedCost.setPromptText("Cost for Climb Speed");
        TextField burrowSpeedCost = new TextField();
        burrowSpeedCost.setId("burrowSpeedCost");
        burrowSpeedCost.setPromptText("Cost for Burrow Speed");
        TextField flySpeedCost = new TextField();
        flySpeedCost.setId("flySpeedCost");
        flySpeedCost.setPromptText("Cost for Fly Speed");
        HBox costBox = new HBox(5);
        costBox.getChildren().addAll(
                walkSpeedCost,
                swimSpeedCost,
                climbSpeedCost,
                burrowSpeedCost,
                flySpeedCost
        );
        costBox.setAlignment(Pos.BASELINE_CENTER);

        Label colorLabel = new Label("Please enter RGB values from 0-255:");
        TextField redValue = new TextField();
        redValue.setId("redValue");
        redValue.setPromptText("Red");
        TextField greenValue = new TextField();
        greenValue.setId("greenValue");
        greenValue.setPromptText("Green");
        TextField blueValue = new TextField();
        blueValue.setId("blueValue");
        blueValue.setPromptText("Blue");
        HBox colorBox = new HBox(5);
        colorBox.getChildren().addAll(
                colorLabel,
                redValue,
                greenValue,
                blueValue
        );
        colorBox.setAlignment(Pos.BASELINE_CENTER);

        Button submit = new Button("Submit");
        submit.setOnAction(e -> clickSubmit());
        submit.setId("terrainSubmit");
        submit.setAlignment(Pos.BASELINE_CENTER);

        terrain.getChildren().addAll(
                nameBox,
                eventsBox,
                passableBox,
                costBox,
                colorBox,
                submit
        );

        //show stage after everything is added to it

        scene = new Scene(terrain, 900, 300);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Handles the submit button. No parameters or returns
     */
    public void clickSubmit() {
        TextField terrainName = (TextField) scene.lookup("#terrainName");
        String name = terrainName.getText();

        CheckBox hasEventOnStartTurnCheckBox = (CheckBox) scene.lookup("#hasEventOnStartTurnCheckBox");
        boolean eventOnStartTurn = hasEventOnStartTurnCheckBox.isSelected();
        CheckBox hasEventOnEnterCheckBox = (CheckBox) scene.lookup("#hasEventOnEnterCheckBox");
        boolean eventOnEnter = hasEventOnEnterCheckBox.isSelected();
        CheckBox hasEventOnEndTurnCheckBox = (CheckBox) scene.lookup("#hasEventOnEndTurnCheckBox");
        boolean eventOnEndTurn = hasEventOnEndTurnCheckBox.isSelected();

        ArrayList<Boolean> isPassableArray = new ArrayList<Boolean>();
        CheckBox isPassableForWalkSpeed = (CheckBox) scene.lookup("#isPassableForWalkSpeed");
        boolean passableForWalking = isPassableForWalkSpeed.isSelected();
        CheckBox isPassableForSwimSpeed = (CheckBox) scene.lookup("#isPassableForSwimSpeed");
        boolean passableForSwimming = isPassableForSwimSpeed.isSelected();
        CheckBox isPassableForClimbSpeed = (CheckBox) scene.lookup("#isPassableForClimbSpeed");
        boolean passableForClimbing = isPassableForClimbSpeed.isSelected();
        CheckBox isPassableForBurrowSpeed = (CheckBox) scene.lookup("#isPassableForBurrowSpeed");
        boolean passableforBurrow = isPassableForBurrowSpeed.isSelected();
        CheckBox isPassableForFlySpeed = (CheckBox) scene.lookup("#isPassableForFlySpeed");
        boolean passableForFlying = isPassableForFlySpeed.isSelected();
        isPassableArray.add(passableForWalking);
        isPassableArray.add(passableForSwimming);
        isPassableArray.add(passableForClimbing);
        isPassableArray.add(passableforBurrow);
        isPassableArray.add(passableForFlying);

        ArrayList<Integer> moveCostArray = new ArrayList<Integer>();
        TextField walkSpeedCost = (TextField) scene.lookup("#walkSpeedCost");
        int costForWalking = 0;
        try {
            costForWalking = Integer.parseInt(walkSpeedCost.getText());
        } catch (NumberFormatException e) {
            costForWalking = 0;
        }

        TextField swimSpeedCost = (TextField) scene.lookup("#swimSpeedCost");
        int costForSwimming = 0;
        try {
            costForSwimming = Integer.parseInt(swimSpeedCost.getText());
        } catch(NumberFormatException e){
            costForSwimming = 0;
        }

        TextField climbSpeedCost = (TextField) scene.lookup("#climbSpeedCost");
        int costForClimbing = 0;
        try {
            costForClimbing = Integer.parseInt(climbSpeedCost.getText());
        } catch (NumberFormatException e) {
            costForClimbing = 0;
        }
        TextField burrowSpeedCost = (TextField) scene.lookup("#burrowSpeedCost");
        int costForBurrowing = 0;
        try {
            costForBurrowing = Integer.parseInt(burrowSpeedCost.getText());
        } catch (NumberFormatException e) {
            costForBurrowing = 0;
        }
        TextField flySpeedCost = (TextField) scene.lookup("#flySpeedCost");
        int costForFlying = 0;
        try {
            costForFlying = Integer.parseInt(flySpeedCost.getText());
        } catch (NumberFormatException e) {
            costForFlying = 0;
        }
        moveCostArray.add(costForWalking);
        moveCostArray.add(costForSwimming);
        moveCostArray.add(costForClimbing);
        moveCostArray.add(costForBurrowing);
        moveCostArray.add(costForFlying);

        TextField redValue = (TextField) scene.lookup("#redValue");
        int red = 0;
        try {
            red = Integer.parseInt(redValue.getText());
        } catch (NumberFormatException e) {
            red = 0;
        }
        TextField greenValue = (TextField) scene.lookup("#redValue");
        int green = 0;
        try {
            green = Integer.parseInt(greenValue.getText());
        } catch (NumberFormatException e) {
            green = 0;
        }
        TextField blueValue = (TextField) scene.lookup("#blueValue");
        int blue = 0;
        try {
            blue = Integer.parseInt(blueValue.getText());
        } catch (NumberFormatException e) {
            blue = 0;
        }
        Color color = Color.rgb(red, green, blue);

        controller.addTerrain(moveCostArray,isPassableArray,eventOnStartTurn,
                eventOnEnter,eventOnEndTurn,name,color);
        stage.close();

    }
}
