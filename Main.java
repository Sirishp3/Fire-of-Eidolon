package sample;

import com.sun.xml.internal.bind.v2.model.core.NonElement;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.stream.FileImageInputStream;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.TreeMap;

public class Main extends Application {
    private int clickToPlay_Page = 0;
    private int selectClass_Page = 1;
    private int Play_Page = 2;
    private int ruleView_Page = 3;
    private int CurrentPage = clickToPlay_Page;

    private int Phase = 1;
    private int TrackPageBeforeMovingToRules = -10;
    private Button goBack = new Button("Back");

    private String Player1_Class = "";
    private String Player2_Class = "";
    private String Player3_Class = "";
    private String Player4_Class = "";
    private String Player5_Class = "";
    private String Player6_Class = "";

    private int[] Player1_Position = {-1,-1};
    private int[] Player2_Position = {-1,-1};
    private int[] Player3_Position = {-1,-1};
    private int[] Player4_Position = {-1,-1};
    private int[] Player5_Position = {-1,-1};
    private int[] Player6_Position = {-1,-1};

    private int CurrentPlayer_Turn = 1;
    private int CurrentPlayer_TotalActionPoints = 3;

    ArrayList<Integer> arrayList_ItemIndexs = new ArrayList<>();
    int playerTrade = -1;


    private Button Player1_move_btn = new Button("Player1 move");
    private Button Player2_move_btn = new Button("Player2 move");
    private Button Player3_move_btn = new Button("Player3 move");
    private Button Player4_move_btn = new Button("Player4 move");
    private Button Player5_move_btn = new Button("Player5 move");
    private Button Player6_move_btn = new Button("Player6 move");

    private Button Player1PickUp_btn = new Button("P1 Pick up");
    private Button Player2PickUp_btn = new Button("P2 Pick up");
    private Button Player3PickUp_btn = new Button("P3 Pick up");
    private Button Player4PickUp_btn = new Button("P4 Pick up");
    private Button Player5PickUp_btn = new Button("P5 Pick up");
    private Button Player6PickUp_btn = new Button("P6 Pick up");

    private Button Give_btn = new Button("Give");
    private Button Take_btn = new Button("Take");

    private Button Done_Trading = new Button("Done Trading");

    private ComboBox<Integer> Player1_Giveto = new ComboBox<Integer>();
    private ComboBox<Integer> Player2_Giveto = new ComboBox<Integer>();
    private ComboBox<Integer> Player3_Giveto = new ComboBox<Integer>();
    private ComboBox<Integer> Player4_Giveto = new ComboBox<Integer>();
    private ComboBox<Integer> Player5_Giveto = new ComboBox<Integer>();
    private ComboBox<Integer> Player6_Giveto = new ComboBox<Integer>();

    private ComboBox<Integer> Player1_TakeFrom = new ComboBox<Integer>();
    private ComboBox<Integer> Player2_TakeFrom = new ComboBox<Integer>();
    private ComboBox<Integer> Player3_TakeFrom = new ComboBox<Integer>();
    private ComboBox<Integer> Player4_TakeFrom = new ComboBox<Integer>();
    private ComboBox<Integer> Player5_TakeFrom = new ComboBox<Integer>();
    private ComboBox<Integer> Player6_TakeFrom = new ComboBox<Integer>();

    private Label Player1_Giveto_lbl = new Label("P1");
    private Label Player2_Giveto_lbl = new Label("P2");
    private Label Player3_Giveto_lbl = new Label("P3");
    private Label Player4_Giveto_lbl = new Label("P4");
    private Label Player5_Giveto_lbl = new Label("P5");
    private Label Player6_Giveto_lbl = new Label("P6");

    private Button DestroyDarkRelic = new Button("Destroy Dark Relic");

    private boolean P1Down = false;
    private boolean P2Down = false;
    private boolean P3Down = false;
    private boolean P4Down = false;
    private boolean P5Down = false;
    private boolean P6Down = false;

    private boolean Player1_clickToMovePlayer = false;
    private boolean Player2_clickToMovePlayer = false;
    private boolean Player3_clickToMovePlayer = false;
    private boolean Player4_clickToMovePlayer = false;
    private boolean Player5_clickToMovePlayer = false;
    private boolean Player6_clickToMovePlayer = false;

    private boolean isClickedOnRitualStack = false;

    private boolean clickedOnChamberCardStack = false;
    private String[][] refGrid = new String[9][9];
    private GraphicsContext gc;
    private int canvasHeight = 1000;
    private int canvasWidth = 1100;
    private int tileLengths = 80;
    private int OutlineHeight = (tileLengths * 9) + 25 + 20;
    private int OutlineWidth = (tileLengths * 9) + 25 + 20;
    private Canvas canvas = new Canvas(canvasWidth, canvasHeight);

    private int countToCancel = 0;

    private Image Cleric_Piece_img          = new Image("File:Cleric_Piece.png",    OutlineWidth, OutlineHeight,false,false);
    private Image DarkKnight_Piece_img      = new Image("File:DarkKnight_Piece.png",     OutlineWidth, OutlineHeight, false, false);
    private Image Engineer_Piece_img        = new Image("File:Engineer_Piece.png",       OutlineWidth, OutlineHeight, false, false);
    private Image Geomancer_Piece_img       = new Image("File:Geomancer_Piece.png",      OutlineWidth, OutlineHeight, false, false);
    private Image MasterSwordsman_Piece_img = new Image("File:MasterSwordsman_Piece.png",OutlineWidth, OutlineHeight, false, false);
    private Image Paladin_Piece_img         = new Image("File:Paladin_Piece.png",        OutlineWidth, OutlineHeight, false, false);
    private Image Ranger_Piece_img          = new Image("File:Ranger_Piece.png",         OutlineWidth, OutlineHeight, false, false);
    private Image Rogue_Piece_img           = new Image("File:Rogue_Piece.png",          OutlineWidth, OutlineHeight, false, false);
    private Image Sage_Piece_img            = new Image("File:Sage_Piece.png",           OutlineWidth, OutlineHeight, false, false);
    private Image Soldier_Piece_img         = new Image("File:Soldier_Piece.png",        OutlineWidth, OutlineHeight, false, false);
    private Image Warrior_Piece_img         = new Image("File:Warrior_Piece.png",        OutlineWidth, OutlineHeight, false, false);
    private Image Wizard_Piece_img          = new Image("File:Wizard_Piece.png",         OutlineWidth, OutlineHeight, false, false);

    private Button AttackCultist = new Button("Attack Cultist");

    private Image Player1_Piece_Image = null;
    private Image Player2_Piece_Image = null;
    private Image Player3_Piece_Image = null;
    private Image Player4_Piece_Image = null;
    private Image Player5_Piece_Image = null;
    private Image Player6_Piece_Image = null;

    private File rules = new File("C:\\Users\\siris\\OneDrive\\Desktop\\FoE_manual_finalprint (1).pdf");

    private Image One = new Image("File:1.png", OutlineWidth, OutlineHeight, false, false);
    private Image Two = new Image("File:3.png", OutlineWidth, OutlineHeight, false, false);
    private Image Three = new Image("File:4.png", OutlineWidth, OutlineHeight, false, false);
    private Image Four = new Image("File:5.png", OutlineWidth, OutlineHeight, false, false);
    private Image Five = new Image("File:7.png", OutlineWidth, OutlineHeight, false, false);
    private Image Six = new Image("File:8.png", OutlineWidth, OutlineHeight, false, false);
    private Image Seven = new Image("File:9.png", OutlineWidth, OutlineHeight, false, false);
    private Image Eight = new Image("File:10.png", OutlineWidth, OutlineHeight, false, false);
    private Image Nine = new Image("File:11.png", OutlineWidth, OutlineHeight, false, false);
    private Image Ten = new Image("File:12.png", OutlineWidth, OutlineHeight, false, false);
    private Image Eleven = new Image("File:13.png", OutlineWidth, OutlineHeight, false, false);
    private Image Twelve = new Image("File:14.png", OutlineWidth, OutlineHeight, false, false);
    private Image Thirteen = new Image("File:15.png", OutlineWidth, OutlineHeight, false, false);
    private Image Fourteen = new Image("File:16.png", OutlineWidth, OutlineHeight, false, false);
    private Image Fifteen = new Image("File:17.png", OutlineWidth, OutlineHeight, false, false);
    private Image Sixteen = new Image("File:18.png", OutlineWidth, OutlineHeight, false, false);
    private Image Seventeen = new Image("File:19.png", OutlineWidth, OutlineHeight, false, false);
    private Image Eighteen = new Image("File:20.png", OutlineWidth, OutlineHeight, false, false);
    private Image Nineteen = new Image("File:21.png", OutlineWidth, OutlineHeight, false, false);
    private Image Twenty = new Image("File:22.png", OutlineWidth, OutlineHeight, false, false);
    private Image Twentyone = new Image("File:23.png", OutlineWidth, OutlineHeight, false, false);
    private Image Twentytwo = new Image("File:24.png", OutlineWidth, OutlineHeight, false, false);
    private Image Twentythree = new Image("File:25.png", OutlineWidth, OutlineHeight, false, false);
    private Image Twentyfour = new Image("File:26.png", OutlineWidth, OutlineHeight, false, false);
    private Image Twentyfive = new Image("File:27.png", OutlineWidth, OutlineHeight, false, false);
    private Image Twentysix = new Image("File:28.png", OutlineWidth, OutlineHeight, false, false);
    private Image Twentyseven = new Image("File:29.png", OutlineWidth, OutlineHeight, false, false);

    private Button Rules_leftbtn = new Button("<");
    private Button Rules_rightbtn = new Button(">");
    private int WhichRulePage = 1;

    ArrayList<String> AlreadyOpenedRitualCardStack = new ArrayList<>();

    private Image AcidJets_Ritual_img       = new Image("File:AcidJets_Ritual.png",tileLengths,tileLengths,false,false);
    private Image ArrowTrap_Ritual_img      = new Image("File:ArrowTrap_Ritual.png",tileLengths,tileLengths,false,false);
    private Image BlueEvent_Ritual_img      = new Image("File:BlueEvent_Ritual.png",tileLengths,tileLengths,false,false);
    private Image DarkSlime_Ritual_img      = new Image("File:DarkSlime_Ritual.png",tileLengths,tileLengths,false,false);
    private Image DenOfSnakes_Ritual_img    = new Image("File:DenOfSnakes_Ritual.png",tileLengths,tileLengths,false,false);
    private Image Dragonling_Ritual_img     = new Image("File:Dragonling_Ritual.png",tileLengths,tileLengths,false,false);
    private Image FelKnight_Ritual_img      = new Image("File:FelKnight_Ritual.png",tileLengths,tileLengths,false,false);
    private Image FloatingStones_Ritual_img = new Image("File:FloatingStones_Ritual.png",tileLengths,tileLengths,false,false);
    private Image GreenEvent_Ritual_img     = new Image("File:GreenEvent_Ritual.png",tileLengths,tileLengths,false,false);
    private Image HallOfIllusion_Ritual_img = new Image("File:HallOfIllusion_Ritual.png",tileLengths,tileLengths,false,false);
    private Image LaughingShadow_Ritual_img = new Image("File:LaughingShadow_Ritual.png",tileLengths,tileLengths,false,false);
    private Image LavaLake_Ritual_img       = new Image("File:LavaLake_Ritual.png",tileLengths,tileLengths,false,false);
    private Image MimicChest_Ritual_img     = new Image("File:MimicChest_Ritual.png",tileLengths,tileLengths,false,false);
    private Image MindEater_Ritual_img      = new Image("File:MindEater_Ritual.png",tileLengths,tileLengths,false,false);
    private Image Minotaur_Ritual_img       = new Image("File:Minotaur_Ritual.png",tileLengths,tileLengths,false,false);
    private Image OgreBrute_Ritual_img      = new Image("File:OgreBrute_Ritual.png",tileLengths,tileLengths,false,false);
    private Image ParadoxPuzzle_Ritual_img  = new Image("File:ParadoxPuzzle_Ritual.png",tileLengths,tileLengths,false,false);
    private Image PendulumBlades_Ritual_img = new Image("File:PendulumBlades_Ritual.png",tileLengths,tileLengths,false,false);
    private Image Psychomancer_Ritual_img   = new Image("File:Psychomancer_Ritual.png",tileLengths,tileLengths,false,false);
    private Image RedEvent_Ritual_img       = new Image("File:RedEvent_Ritual.png",tileLengths,tileLengths,false,false);
    private Image SkeletalGuards_Ritual_img = new Image("File:SkeletalGuards_Ritual.png",tileLengths,tileLengths,false,false);
    private Image SphynxsRiddle_Ritual_img  = new Image("File:SphynxsRiddle_Ritual.png",tileLengths,tileLengths,false,false);
    private Image SpikedPit_Ritual_img      = new Image("File:SpikedPit_Ritual.png",tileLengths,tileLengths,false,false);
    private Image VoraciousPlant_Ritual_img = new Image("File:VoraciousPlant_Ritual.png",tileLengths,tileLengths,false,false);

    private int AcidJets_CultistTokenCount = 0;
    private int ArrowTrap_CultistTokenCount = 0;
    private int BlueEvent_CultistTokenCount = 0;
    private int DarkSlime_CultistTokenCount = 0;
    private int DenOfSnakes_CultistTokenCount = 0;
    private int Dragonling_CultistTokenCount = 0;
    private int FelKnight_CultistTokenCount = 0;
    private int FloatingStones_CultistTokenCount = 0;
    private int GreenEvent_CultistTokenCount = 0;
    private int HallOfIllusions_CultistTokenCount = 0;
    private int LaughingShadow_CultistTokenCount = 0;
    private int LavaLake_CultistTokenCount = 0;
    private int MimicChest_CultistTokenCount = 0;
    private int MindEater_CultistTokenCount = 0;
    private int Minotaur_CultistTokenCount = 0;
    private int OgreBrute_CultistTokenCount = 0;
    private int ParadoxPuzzle_CultistTokenCount = 0;
    private int PendulumBlades_CultistTokenCount = 0;
    private int Psychomancer_CultistTokenCount = 0;
    private int RedEvent_CultistTokenCount = 0;
    private int SkeletalGuards_CultistTokenCount = 0;
    private int SphynxsRiddle_CultistTokenCount = 0;
    private int SpikedPit_CultistTokenCount = 0;
    private int VoraciousPlant_CultistTokenCount = 0;

    private ArrayList<String> StackOfRitualCards = new ArrayList<>();

    private Image Outline = new Image("File:Outline.png", OutlineWidth, OutlineHeight, false, false);
    private Image Vestibule_img         = new Image("File:Vestibule.png", tileLengths, tileLengths, false, false);
    private Image AcidJets_img          = new Image("File:AcidJets.png", tileLengths, tileLengths, false, false);
    private Image ArrowTraps_img        = new Image("File:ArrowTrap.png", tileLengths, tileLengths, false, false);
    private Image Blank_img             = new Image("File:Blank.png", tileLengths, tileLengths, false, false);
    private Image DarkSlime_img         = new Image("File:DarkSlime.png", tileLengths, tileLengths, false, false);
    private Image DenOfSnakes_img       = new Image("File:DenOfSnakes.png", tileLengths, tileLengths, false, false);
    private Image Dragonling_img        = new Image("File:Dragonling.png", tileLengths, tileLengths, false, false);
    private Image FelKnight_img         = new Image("File:FelKnight.png", tileLengths, tileLengths, false, false);
    private Image FireOfEidolon_img     = new Image("File:FireOfEidolon.png", tileLengths, tileLengths, false, false);
    private Image FloatingStones_img    = new Image("File:FloatingStones.png", tileLengths, tileLengths, false, false);
    private Image HallOfIllusion_img    = new Image("File:HallOfIllusion.png", tileLengths, tileLengths, false, false);
    private Image LaughingShadow_img    = new Image("File:LaughingShadow.png", tileLengths, tileLengths, false, false);
    private Image LavaLake_img          = new Image("File:LavaLake.png", tileLengths, tileLengths, false, false);
    private Image MimicChest_img        = new Image("File:MimicChest.png", tileLengths, tileLengths, false, false);
    private Image MindEater_img         = new Image("File:MindEater.png", tileLengths, tileLengths, false, false);
    private Image Minotaur_img          = new Image("File:Minotaur.png", tileLengths, tileLengths, false, false);
    private Image NewExit_img           = new Image("File:NewExit.png", tileLengths, tileLengths, false, false);
    private Image OgreBrute_img         = new Image("File:OgreBrute.png", tileLengths, tileLengths, false, false);
    private Image ParadoxPuzzle_img     = new Image("File:ParadoxPuzzle.png", tileLengths, tileLengths, false, false);
    private Image PendulumBlades_img    = new Image("File:PendulumBlades.png", tileLengths, tileLengths, false, false);
    private Image Psychomancer_img      = new Image("File:Psychomancer.png", tileLengths, tileLengths, false, false);
    private Image SecretPassageX_img    = new Image("File:SecretPassageX.png", tileLengths, tileLengths, false, false);
    private Image SecretPassageY_img    = new Image("File:SecretPassageY.png", tileLengths, tileLengths, false, false);
    private Image SkeletalGuards_img    = new Image("File:SkeletalGuards.png", tileLengths, tileLengths, false, false);
    private Image SphynxsRiddle_img     = new Image("File:SphynxsRiddle.png", tileLengths, tileLengths, false, false);
    private Image SpikedPit_img         = new Image("File:SpikedPit.png", tileLengths, tileLengths, false, false);
    private Image VoraciousPlant_img    = new Image("File:VoraciousPlant.png", tileLengths, tileLengths, false, false);
    private Image VoraxsFocus_img       = new Image("File:VoraxsFocus.png", tileLengths, tileLengths, false, false);
    private Image VoraxsHeart_img       = new Image("File:VoraxsHeart.png", tileLengths, tileLengths, false, false);
    private Image VoraxsKnowledge_img   = new Image("File:VoraxsKnowledge.png", tileLengths, tileLengths, false, false);

    private String AcidJets_PowerToken =  "Key";
    private String ArrowTraps_PowerToken = "Key";
    private String DarkSlime_PowerToken = "Pick";
    private String DenOfSnakes_PowerToken = "Key";
    private String Dragonling_PowerToken = "Pick";
    private String FelKnight_PowerToken = "Pick";
    private String FireOfEidolon_DarkRelic = "Fireball";
    private String FloatingStones_PowerToken = "Key";
    private String HallOfIllusion_PowerToken = "Scroll";
    private String LaughingShadow_PowerToken = "Scroll";
    private String LavaLake_PowerToken = "Key";
    private String MimicChest_PowerToken = "Scroll";
    private String MindEater_PowerToken = "Scroll";
    private String Minotaur_PowerToken = "Pick";
    private String OgreBrute_PowerToken = "Pick";
    private String ParadoxPuzzle_PowerToken = "Scroll";
    private String PendulumBlades_PowerToken = "Key";
    private String Psychomancer_PowerToken = "Scroll";
    private String SkeletalGuards_PowerToken = "Pick";
    private String SphynxsRiddle_PowerToken = "Scroll";
    private String SpikedPit_PowerToken = "Key";
    private String VoraciousPlant_PowerToken = "Pick";
    private String VoraxsFocus_DarkRelic = "Gem";
    private String VoraxsHeart_DarkRelic = "Heart";
    private String VoraxsKnowledge_DarkRelic = "Book";
    //4-30-2021 Start working here
    private Image Key = new Image("File:Key.png",tileLengths,tileLengths,false,false);
    private Image Pick = new Image("File:Pick.png",tileLengths,tileLengths,false,false);
    private Image Scroll = new Image("File:Scroll.png",tileLengths,tileLengths,false,false);

    private Image Heart_img = new Image("File:Heart.png",tileLengths,tileLengths,false,false);
    private Image Book_img  = new Image("File:Book.png",tileLengths,tileLengths,false,false);
    private Image Gem_img   = new Image("File:Gem.png",tileLengths,tileLengths,false,false);

    private Image BrokenHeart_img = new Image("File:BrokenHeart.png",tileLengths,tileLengths,false,false);
    private Image BrokenBook_img  = new Image("File:BrokenBook.png",tileLengths,tileLengths,false,false);
    private Image BrokenGem_img   = new Image("File:BrokenGem.png",tileLengths,tileLengths,false,false);

    private Image Fireball_img    = new Image("File:Fireball.png",tileLengths,tileLengths,false,false);

    private boolean isHeartBroken = false;
    private boolean isBookBroken = false;
    private boolean isGemBroken = false;

    private ArrayList<String> Player1Inventory = new ArrayList<>();
    private ArrayList<String> Player2Inventory = new ArrayList<>();
    private ArrayList<String> Player3Inventory = new ArrayList<>();
    private ArrayList<String> Player4Inventory = new ArrayList<>();
    private ArrayList<String> Player5Inventory = new ArrayList<>();
    private ArrayList<String> Player6Inventory = new ArrayList<>();

    private ArrayList<String> AcidJets_doorplacements =  new ArrayList<>();
    private ArrayList<String> ArrowTraps_doorplacements = new ArrayList<>();
    private ArrayList<String> Blank_doorplacements = new ArrayList<>();
    private ArrayList<String> DarkSlime_doorplacements = new ArrayList<>();
    private ArrayList<String> DenOfSnakes_doorplacements = new ArrayList<>();
    private ArrayList<String> Dragonling_doorplacements = new ArrayList<>();
    private ArrayList<String> FelKnight_doorplacements = new ArrayList<>();
    private ArrayList<String>FireOfEidolon_doorplacements = new ArrayList<>();
    private ArrayList<String>FloatingStones_doorplacements = new ArrayList<>();
    private ArrayList<String>HallOfIllusion_doorplacements = new ArrayList<>();
    private ArrayList<String>LaughingShadow_doorplacements = new ArrayList<>();
    private ArrayList<String>LavaLake_doorplacements = new ArrayList<>();
    private ArrayList<String>MimicChest_doorplacements = new ArrayList<>();
    private ArrayList<String>MindEater_doorplacements = new ArrayList<>();
    private ArrayList<String>Minotaur_doorplacements = new ArrayList<>();
    private ArrayList<String>NewExit_doorplacements = new ArrayList<>();
    private ArrayList<String>OgreBrute_doorplacements = new ArrayList<>();
    private ArrayList<String>ParadoxPuzzle_doorplacements = new ArrayList<>();
    private ArrayList<String>PendulumBlades_doorplacements = new ArrayList<>();
    private ArrayList<String>Psychomancer_doorplacements = new ArrayList<>();
    private ArrayList<String>SecretPassageX_doorplacements = new ArrayList<>();
    private ArrayList<String>SecretPassageY_doorplacements = new ArrayList<>();
    private ArrayList<String>SkeletalGuards_doorplacements = new ArrayList<>();
    private ArrayList<String>SphynxsRiddle_doorplacements = new ArrayList<>();
    private ArrayList<String>SpikedPit_doorplacements = new ArrayList<>();
    private ArrayList<String>Vestibule_doorplacements = new ArrayList<>();
    private ArrayList<String>VoraciousPlant_doorplacements = new ArrayList<>();
    private ArrayList<String>VoraxsFocus_doorplacements = new ArrayList<>();
    private ArrayList<String>VoraxsHeart_doorplacements = new ArrayList<>();
    private ArrayList<String>VoraxsKnowledge_doorplacements = new ArrayList<>();

    private Image Cleric = new Image("File:Cleric.png");
    private Image DarkKnight = new Image("File:DarkKnight.png");
    private Image Engineer = new Image("File:Engineer.png");
    private Image Geomancer = new Image("File:Geomancer.png");
    private Image Paladin = new Image("File:Paladin.png");
    private Image Ranger = new Image("File:Ranger.png");
    private Image Rogue = new Image("File:Rogue.png");
    private Image Sage = new Image("File:Sage.png");
    private Image Soldier = new Image("File:Soldier.png");
    private Image Swordsman = new Image("File:Swordsman.png");
    private Image Warrior = new Image("File:Warrior.png");
    private Image Wizard = new Image("File:Wizard.png");

    private Image NormalThreatLevel_img = new Image("File:Normal.png");
    private ArrayList<Integer> NormalThreatLevel_NumOfCards = new ArrayList<>();
    private int ThreatIndex;

    private int[] Cleric_pickUp_levels = {2,3,1};
    private int[] DarkKnight_pickUp_levels = {1,3,2};
    private int[] Engineer_pickUp_levels = {2,2,2};
    private int[] Geomancer_pickUp_levels = {3,2,1};
    private int[] Paladin_pickUp_levels = {1,3,2};
    private int[] Ranger_pickUp_levels = {2,1,3};
    private int[] Rogue_pickUp_levels = {3,1,2};
    private int[] Sage_pickUp_levels = {2,3,1};
    private int[] Soldier_pickUp_levels = {2,1,3};
    private int[] Swordsman_pickUp_levels = {2,2,2};
    private int[] Warrior_pickUp_levels = {1,2,3};
    private int[] Wizard_pickUp_levels = {3,2,1};

    private ArrayList<String> stackOfChamberTiles = new ArrayList<String>();
    private ArrayList<String> stackOfChamberTiles_DoorPlacements = new ArrayList<String>();


    private int NumOfPlayers = 0;
    private GridPane grid = new GridPane();
    private MenuBar menuBar = new MenuBar();
    private Menu menuPlayer = new Menu("Players");
    private Menu menuRules = new Menu("Rules");
    private MenuItem menuRules_btn = new MenuItem("View Rules");

    private ComboBox<String> Player1Class_CB = new ComboBox<>();
    private ComboBox<String> Player2Class_CB = new ComboBox<>();
    private ComboBox<String> Player3Class_CB = new ComboBox<>();
    private ComboBox<String> Player4Class_CB = new ComboBox<>();
    private ComboBox<String> Player5Class_CB = new ComboBox<>();
    private ComboBox<String> Player6Class_CB = new ComboBox<>();

    private Label player1_lbl = new Label("Player1:");
    private Label player2_lbl = new Label("Player2:");
    private Label player3_lbl = new Label("Player3:");
    private Label player4_lbl = new Label("Player4:");
    private Label player5_lbl = new Label("Player5:");
    private Label player6_lbl = new Label("Player6:");

    private Button dive = new Button("Dive");
    private boolean playersDoneSelecting = false;

    private int count = 0;

    Button verifyPlacement = new Button("Verify");
    Button rotate_btn = new Button("Rotate");

    Button wait = new Button("Wait or Next turn");

    int tradeCountOfSameItem =0;
    @Override
    public void start(Stage primaryStage) throws Exception {

        NormalThreatLevel_NumOfCards.add(4);
        NormalThreatLevel_NumOfCards.add(3);
        NormalThreatLevel_NumOfCards.add(3);
        NormalThreatLevel_NumOfCards.add(3);
        NormalThreatLevel_NumOfCards.add(2);
        NormalThreatLevel_NumOfCards.add(2);
        NormalThreatLevel_NumOfCards.add(1);
        NormalThreatLevel_NumOfCards.add(2);
        NormalThreatLevel_NumOfCards.add(1);


        ThreatIndex=NormalThreatLevel_NumOfCards.size()-1;


        addAllInventoryItemsToTakeFromCB();
        addAllInventoryItemsToGiveToCB();

        StackOfRitualCards.add("AcidJets_Ritual");
        StackOfRitualCards.add("ArrowTrap_Ritual");
        StackOfRitualCards.add("BlueEvent_Ritual");
        StackOfRitualCards.add("DarkSlime_Ritual");
        StackOfRitualCards.add("DenOfSnakes_Ritual");
        StackOfRitualCards.add("Dragonling_Ritual");
        StackOfRitualCards.add("FelKnight_Ritual");
        StackOfRitualCards.add("FloatingStones_Ritual");
        StackOfRitualCards.add("GreenEvent_Ritual");
        StackOfRitualCards.add("HallOfIllusion_Ritual");
        StackOfRitualCards.add("LaughingShadow_Ritual");
        StackOfRitualCards.add("LavaLake_Ritual");
        StackOfRitualCards.add("MimicChest_Ritual");
        StackOfRitualCards.add("MindEater_Ritual");
        StackOfRitualCards.add("Minotaur_Ritual");
        StackOfRitualCards.add("OgreBrute_Ritual");
        StackOfRitualCards.add("ParadoxPuzzle_Ritual");
        StackOfRitualCards.add("PendulumBlades_Ritual");
        StackOfRitualCards.add("Psychomancer_Ritual");
        StackOfRitualCards.add("RedEvent_Ritual");
        StackOfRitualCards.add("SkeletalGuards_Ritual");
        StackOfRitualCards.add("SphynxsRiddle_Ritual");
        StackOfRitualCards.add("SpikedPit_Ritual");
        StackOfRitualCards.add("VoraciousPlant_Ritual");



        AcidJets_doorplacements.add("up");
        AcidJets_doorplacements.add("down");

        ArrowTraps_doorplacements .add("left");
        ArrowTraps_doorplacements .add("down");
        ArrowTraps_doorplacements .add("right");

        DarkSlime_doorplacements.add("right");
        DarkSlime_doorplacements.add("down");

        DenOfSnakes_doorplacements.add("up");
        DenOfSnakes_doorplacements.add("right");
        DenOfSnakes_doorplacements.add("down");

        Dragonling_doorplacements.add("up");
        Dragonling_doorplacements.add("left");
        Dragonling_doorplacements.add("down");

        FelKnight_doorplacements.add("up");
        FelKnight_doorplacements.add("right");
        FelKnight_doorplacements.add("down");

         FireOfEidolon_doorplacements.add("down");

         FloatingStones_doorplacements.add("up");
         FloatingStones_doorplacements.add("right");
         FloatingStones_doorplacements.add("down");
         FloatingStones_doorplacements.add("left");

         HallOfIllusion_doorplacements.add("right");
         HallOfIllusion_doorplacements.add("down");

         LaughingShadow_doorplacements.add("up");
         LaughingShadow_doorplacements.add("down");
         LaughingShadow_doorplacements.add("right");
         LaughingShadow_doorplacements.add("left");

         LavaLake_doorplacements.add("left");
         LavaLake_doorplacements.add("down");

         MimicChest_doorplacements.add("down");

         MindEater_doorplacements.add("left");
         MindEater_doorplacements.add("right");
         MindEater_doorplacements.add("down");

         Minotaur_doorplacements.add("up");
         Minotaur_doorplacements.add("down");
         Minotaur_doorplacements.add("right");
         Minotaur_doorplacements.add("left");

         NewExit_doorplacements.add("down");

         OgreBrute_doorplacements.add("left");
         OgreBrute_doorplacements.add("down");


         ParadoxPuzzle_doorplacements.add("up");
         ParadoxPuzzle_doorplacements.add("left");
         ParadoxPuzzle_doorplacements.add("down");

         PendulumBlades_doorplacements.add("up");
         PendulumBlades_doorplacements.add("down");


        Psychomancer_doorplacements.add("up");
        Psychomancer_doorplacements.add("down");
        Psychomancer_doorplacements.add("right");
        Psychomancer_doorplacements.add("left");

        SecretPassageX_doorplacements.add("up");;
        SecretPassageX_doorplacements.add("down");;
        SecretPassageX_doorplacements.add("right");;
        SecretPassageX_doorplacements.add("left");;


        SecretPassageY_doorplacements.add("up");
        SecretPassageY_doorplacements.add("down");
        SecretPassageY_doorplacements.add("right");
        SecretPassageY_doorplacements.add("left");


        SkeletalGuards_doorplacements.add("up");
        SkeletalGuards_doorplacements.add("down");

        SphynxsRiddle_doorplacements.add("up");
        SphynxsRiddle_doorplacements.add("down");

        SpikedPit_doorplacements.add("up");
        SpikedPit_doorplacements.add("right");
        SpikedPit_doorplacements.add("down");

        Vestibule_doorplacements.add("up");
        Vestibule_doorplacements.add("down");
        Vestibule_doorplacements.add("right");
        Vestibule_doorplacements.add("left");

        VoraciousPlant_doorplacements.add("up");
        VoraciousPlant_doorplacements.add("down");
        VoraciousPlant_doorplacements.add("right");
        VoraciousPlant_doorplacements.add("left");

        VoraxsFocus_doorplacements.add("down");

        VoraxsHeart_doorplacements.add("down");

        VoraxsKnowledge_doorplacements.add("down");


        stackOfChamberTiles.add("AcidJets");
        stackOfChamberTiles.add("ArrowTraps");
        stackOfChamberTiles.add("DarkSlime");
        stackOfChamberTiles.add("DenOfSnakes");
        stackOfChamberTiles.add("Dragonling");
        stackOfChamberTiles.add("FelKnight");
        stackOfChamberTiles.add("FireOfEidolon");
        stackOfChamberTiles.add("FloatingStones");
        stackOfChamberTiles.add("HallOfIllusion");
        stackOfChamberTiles.add("LaughingShadow");
        stackOfChamberTiles.add("LavaLake");
        stackOfChamberTiles.add("MimicChest");
        stackOfChamberTiles.add("MindEater");
        stackOfChamberTiles.add("Minotaur");
        stackOfChamberTiles.add("NewExit");
        stackOfChamberTiles.add("OgreBrute");
        stackOfChamberTiles.add("ParadoxPuzzle");
        stackOfChamberTiles.add("PendulumBlades");
        stackOfChamberTiles.add("Psychomancer");
        stackOfChamberTiles.add("SecretPassageX");
        stackOfChamberTiles.add("SecretPassageY");
        stackOfChamberTiles.add("SkeletalGuards");
        stackOfChamberTiles.add("SphynxsRiddle");
        stackOfChamberTiles.add("SpikedPit");
        stackOfChamberTiles.add("VoraciousPlant");
        stackOfChamberTiles.add("VoraxsFocus");
        stackOfChamberTiles.add("VoraxsHeart");
        stackOfChamberTiles.add("VoraxsKnowledge");

        randomizeStack();

        refGrid[4][4] = "Vestibule";
        RadioMenuItem OnePlayer = new RadioMenuItem("1");
        RadioMenuItem TwoPlayer = new RadioMenuItem("2");
        RadioMenuItem ThreePlayer = new RadioMenuItem("3");
        RadioMenuItem FourPlayer = new RadioMenuItem("4");
        RadioMenuItem FivePlayer = new RadioMenuItem("5");
        RadioMenuItem SixPlayer = new RadioMenuItem("6");
        RadioMenuItem SevenPlayer = new RadioMenuItem("7");
        RadioMenuItem EightPlayer = new RadioMenuItem("8");
        RadioMenuItem NinePlayer = new RadioMenuItem("9");
        RadioMenuItem TenPlayer = new RadioMenuItem("10");

        ToggleGroup playerList_ToggleGroup = new ToggleGroup();
        playerList_ToggleGroup.getToggles().addAll(OnePlayer, TwoPlayer, ThreePlayer, FourPlayer, FivePlayer, SixPlayer, SevenPlayer, EightPlayer, NinePlayer, TenPlayer);
        menuPlayer.getItems().addAll(OnePlayer, TwoPlayer, ThreePlayer, FourPlayer, FivePlayer, SixPlayer, SevenPlayer, EightPlayer, NinePlayer, TenPlayer);
        menuBar.getMenus().add(menuPlayer);
        menuRules.getItems().add(menuRules_btn);
        menuBar.getMenus().add(menuRules);
        grid.setGridLinesVisible(false);

        verifyPlacement.setMinSize(tileLengths, 25 + 10 + tileLengths);
        rotate_btn.setMinSize(tileLengths, rotate_btn.getHeight());

        grid.add(menuBar, 0, 0, 10, 1);
        grid.add(canvas, 0, 1, 4, 25);

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Fire of Eidolon");
        Group group = new Group();
        group.getChildren().addAll(grid);
        gc = canvas.getGraphicsContext2D();
        drawToken();
        drawThreatLevel();
        if (CurrentPage == clickToPlay_Page)
            drawClickToPlayPage(gc);
        menuRules_btn.setOnAction(event -> {
            drawRules(gc, One);
            TrackPageBeforeMovingToRules = CurrentPage;
            CurrentPage = ruleView_Page;
            verifyPlacement.setVisible(false);
            rotate_btn.setVisible(false);

            Player1Class_CB.setVisible(false);
            Player2Class_CB.setVisible(false);
            Player3Class_CB.setVisible(false);
            Player4Class_CB.setVisible(false);
            Player5Class_CB.setVisible(false);
            Player6Class_CB.setVisible(false);

            player1_lbl.setVisible(false);
            player2_lbl.setVisible(false);
            player3_lbl.setVisible(false);
            player4_lbl.setVisible(false);
            player5_lbl.setVisible(false);
            player6_lbl.setVisible(false);

            Rules_rightbtn.setVisible(true);
            Rules_leftbtn.setVisible(true);
            goBack.setVisible(true);

            grid.add(Rules_leftbtn, 4, 3, 1, 1);
            grid.add(Rules_rightbtn, 4, 4, 1, 1);
            grid.add(goBack, 4, 5, 1, 1);




        });
        goBack.setOnAction(event -> {
            CurrentPage = TrackPageBeforeMovingToRules;
            Rules_leftbtn.setVisible(false);
            Rules_rightbtn.setVisible(false);
            drawSelectingPlayerAndClassesPage(gc);
            if (TrackPageBeforeMovingToRules == clickToPlay_Page) {
                drawClickToPlayPage(gc);
            }
            if (TrackPageBeforeMovingToRules == selectClass_Page) {
                if (Player1Class_CB.getSelectionModel().getSelectedItem() != null) {
                    drawPlayerClasses(gc, 1, Player1_Class);
                }
                if (Player2Class_CB.getSelectionModel().getSelectedItem() != null) {
                    drawPlayerClasses(gc, 2, Player2_Class);
                }
                if (Player3Class_CB.getSelectionModel().getSelectedItem() != null) {
                    drawPlayerClasses(gc, 3, Player3_Class);
                }
                if (Player4Class_CB.getSelectionModel().getSelectedItem() != null) {
                    drawPlayerClasses(gc, 4, Player4_Class);
                }
                if (Player5Class_CB.getSelectionModel().getSelectedItem() != null) {
                    drawPlayerClasses(gc, 5, Player5_Class);
                }
                if (Player6Class_CB.getSelectionModel().getSelectedItem() != null) {
                    drawPlayerClasses(gc, 6, Player6_Class);
                }

                Player1Class_CB.setVisible(true);
                Player2Class_CB.setVisible(true);
                Player3Class_CB.setVisible(true);
                Player4Class_CB.setVisible(true);
                Player5Class_CB.setVisible(true);
                Player6Class_CB.setVisible(true);

                player1_lbl.setVisible(true);
                player2_lbl.setVisible(true);
                player3_lbl.setVisible(true);
                player4_lbl.setVisible(true);
                player5_lbl.setVisible(true);
                player6_lbl.setVisible(true);

            }
            if (TrackPageBeforeMovingToRules == Play_Page) {
                verifyPlacement.setVisible(true);
                rotate_btn.setVisible(true);
                drawPlayPage(gc);
                drawRefBoardAgain();
            }
            goBack.setVisible(false);
        });
        Rules_leftbtn.setOnAction(event -> {
            if (WhichRulePage > 1) {
                WhichRulePage -= 1;
                drawRules(gc, getRulePage(WhichRulePage));
            }
        });
        Rules_rightbtn.setOnAction(event -> {
            if (WhichRulePage < 27) {
                WhichRulePage += 1;
                drawRules(gc, getRulePage(WhichRulePage));
            }
        });
        OnePlayer.setOnAction(event -> {
            NumOfPlayers = 1;
            System.out.println(NumOfPlayers);
        });
        TwoPlayer.setOnAction(event -> {
            NumOfPlayers = 2;
            System.out.println(NumOfPlayers);

        });
        ThreePlayer.setOnAction(event -> {
            NumOfPlayers = 3;
            System.out.println(NumOfPlayers);
        });
        FourPlayer.setOnAction(event -> {
            NumOfPlayers = 4;
            System.out.println(NumOfPlayers);
        });
        FivePlayer.setOnAction(event -> {
            NumOfPlayers = 5;
            System.out.println(NumOfPlayers);
        });
        SixPlayer.setOnAction(event -> {
            NumOfPlayers = 6;
            System.out.println(NumOfPlayers);
        });
        canvas.setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();
            if (CurrentPage == selectClass_Page) {
                if (count == 1 && playersDoneSelecting == true)
                {
                    CurrentPage = Play_Page;
                    drawPlayPage(gc);
                    drawToken();
                    drawHeros();
                }
                drawWinAndCheckForWin();
            }
            if (CurrentPage == clickToPlay_Page) {
                if (count == 0 && NumOfPlayers > 0) {
                    CurrentPage = selectClass_Page;
                    drawSelectingPlayerAndClassesPage(gc);
                    AddToPlayerClasses(Player1Class_CB);
                    AddToPlayerClasses(Player2Class_CB);
                    AddToPlayerClasses(Player3Class_CB);
                    AddToPlayerClasses(Player4Class_CB);
                    AddToPlayerClasses(Player5Class_CB);
                    AddToPlayerClasses(Player6Class_CB);
                    if (NumOfPlayers == 1) {
                        grid.add(player1_lbl, 12, 0, 1, 1);
                        grid.add(Player1Class_CB, 12, 1, 1, 1);
                    }
                    if (NumOfPlayers == 2) {
                        grid.add(player1_lbl, 11, 0, 1, 1);
                        grid.add(Player1Class_CB, 11, 1, 1, 1);
                        grid.add(player2_lbl, 11, 2, 1, 1);
                        grid.add(Player2Class_CB, 11, 3, 1, 1);
                    }
                    if (NumOfPlayers == 3) {
                        grid.add(player1_lbl, 11, 0, 1, 1);
                        grid.add(Player1Class_CB, 11, 1, 1, 1);
                        grid.add(player2_lbl, 11, 2, 1, 1);
                        grid.add(Player2Class_CB, 11, 3, 1, 1);
                        grid.add(player3_lbl, 11, 4, 1, 1);
                        grid.add(Player3Class_CB, 11, 5, 1, 1);
                    }
                    if (NumOfPlayers == 4) {
                        grid.add(player1_lbl, 11, 0, 1, 1);
                        grid.add(Player1Class_CB, 11, 1, 1, 1);
                        grid.add(player2_lbl, 11, 2, 1, 1);
                        grid.add(Player2Class_CB, 11, 3, 1, 1);
                        grid.add(player3_lbl, 11, 4, 1, 1);
                        grid.add(Player3Class_CB, 11, 5, 1, 1);
                        grid.add(player4_lbl, 11, 6, 1, 1);
                        grid.add(Player4Class_CB, 11, 7, 1, 1);
                    }
                    if (NumOfPlayers == 5) {
                        grid.add(player1_lbl, 11, 0, 1, 1);
                        grid.add(Player1Class_CB, 11, 1, 1, 1);
                        grid.add(player2_lbl, 11, 2, 1, 1);
                        grid.add(Player2Class_CB, 11, 3, 1, 1);
                        grid.add(player3_lbl, 11, 4, 1, 1);
                        grid.add(Player3Class_CB, 11, 5, 1, 1);
                        grid.add(player4_lbl, 11, 6, 1, 1);
                        grid.add(Player4Class_CB, 11, 7, 1, 1);
                        grid.add(player5_lbl, 11, 8, 1, 1);
                        grid.add(Player5Class_CB, 11, 9, 1, 1);
                    }
                    if (NumOfPlayers == 6) {
                        grid.add(player1_lbl, 11, 0, 1, 1);
                        grid.add(Player1Class_CB, 11, 1, 1, 1);
                        grid.add(player2_lbl, 11, 2, 1, 1);
                        grid.add(Player2Class_CB, 11, 3, 1, 1);
                        grid.add(player3_lbl, 11, 4, 1, 1);
                        grid.add(Player3Class_CB, 11, 5, 1, 1);
                        grid.add(player4_lbl, 11, 6, 1, 1);
                        grid.add(Player4Class_CB, 11, 7, 1, 1);
                        grid.add(player5_lbl, 11, 8, 1, 1);
                        grid.add(Player5Class_CB, 11, 9, 1, 1);
                        grid.add(player6_lbl, 11, 10, 1, 1);
                        grid.add(Player6Class_CB, 11, 11, 1, 1);
                    }
                    count++;
                }
                drawWinAndCheckForWin();
            }

            if (CurrentPage == Play_Page) {
                if (checkIfClickOnRitualStack(x, y) == true) {
                        pickTopCardFromRitualDeckAndDrawToTheSideAndOnBoard();
                    } else if (clickedOnChamberCardStack == true) {
                        FillEmptySpace();
                        int[] rowAndCol = {getRowAndCol(x, y)[0], getRowAndCol(x, y)[1]};
                        ArrayList<int[]> placements = getAvailablePlacements();
                        for (int[] t : placements) {
                            System.out.println(t[0] + " " + t[1]);
                        }
                        boolean check = false;
                        for (int[] t : placements) {
                            if (t[0] == rowAndCol[0]) {
                                if (t[1] == rowAndCol[1]) {
                                    check = true;
                                }
                            }

                        }
                        if (refGrid[getRowAndCol(x, y)[0]][getRowAndCol(x, y)[1]] == null&& check == true) {
                            clickedOnChamberCardStack = false;
                            highlightCard(gc, getRowAndCol(x, y)[0], getRowAndCol(x, y)[1]);
                        }
                        drawWinAndCheckForWin();
                    rotate_btn.setOnMouseClicked(event1 -> {
                            Image LOC = stringToImage(stackOfChamberTiles.get(0));

                            ImageView iv = new ImageView(LOC);
                            iv.setRotate(90);

                            SnapshotParameters params = new SnapshotParameters();
                            params.setFill(Color.TRANSPARENT);

                            Image rotatedImage = iv.snapshot(params, null);
                            setRotateImage(rotatedImage);
                            rotateDoorPlacement();
                            drawCardToTheSideForPlacement(gc);
                        });
                    drawWinAndCheckForWin();
                    boolean finalCheck = check;
                        verifyPlacement.setOnMouseClicked(e -> {
                            if (stackOfChamberTiles.size() > 0 && refGrid[getRowAndCol(x, y)[0]][getRowAndCol(x, y)[1]] == null&& finalCheck && VerifyIfDoorsConnect(getRowAndCol(x, y)[0], getRowAndCol(x, y)[1])) {
                                drawCardOnBoard(gc, stringToImage(stackOfChamberTiles.get(0)), getRowAndCol(x, y)[0], getRowAndCol(x, y)[1]);
                                drawToken();
                                drawHeros();
                                refGrid[getRowAndCol(x, y)[0]][getRowAndCol(x, y)[1]] = stackOfChamberTiles.get(0);
                                System.out.println(stackOfChamberTiles.get(0));
                                stackOfChamberTiles.remove(0);
                                CurrentPlayer_TotalActionPoints -= 1;
                                drawActionPoints();
                                setTurnToNext();
                                gc.setFill(Color.GRAY);
                                gc.fillRect((10 + Outline.getWidth() + 20) + (tileLengths) + 20, 20, 200, 200);
                            }
                            drawToken();
                        });
                    }
                drawWinAndCheckForWin();
                if (clickedOnChamberCardStack == false) {
                        if (event.getX() > 10 + Outline.getWidth() + 20 && event.getY() > 10 + 20) {
                            if (event.getX() < (10 + Outline.getWidth() + 20) + (tileLengths) && event.getY() < 10 + 20 + tileLengths) {
                                clickedOnChamberCardStack = true;
                                drawCardToTheSideForPlacement(gc);

                            }
                        }
                    }
                drawWinAndCheckForWin();
                    if (Player1_clickToMovePlayer == true) {
                        moveIntoRoom(1, getRowAndCol(x, y)[0], getRowAndCol(x, y)[1]);
                        drawRefBoardAgain();
                        drawToken();
                        drawHeros();
                        Player1_clickToMovePlayer = false;
                        setTurnToNext();
                        System.out.println(CurrentPlayer_TotalActionPoints);
                        PlayerOnSameTileSetVisible();
                        drawWinAndCheckForWin();
                    }
                    if (Player2_clickToMovePlayer == true) {
                        moveIntoRoom(2, getRowAndCol(x, y)[0], getRowAndCol(x, y)[1]);
                        drawRefBoardAgain();
                        drawToken();
                        drawHeros();
                        Player2_clickToMovePlayer = false;
                        setTurnToNext();
                        System.out.println(CurrentPlayer_TotalActionPoints);
                        PlayerOnSameTileSetVisible();
                        drawWinAndCheckForWin();
                    }
                    if (Player3_clickToMovePlayer == true) {
                        moveIntoRoom(3, getRowAndCol(x, y)[0], getRowAndCol(x, y)[1]);
                        drawRefBoardAgain();
                        drawToken();
                        drawHeros();
                        Player3_clickToMovePlayer = false;
                        setTurnToNext();
                        System.out.println(CurrentPlayer_TotalActionPoints);
                        PlayerOnSameTileSetVisible();
                        drawWinAndCheckForWin();
                    }
                    if (Player4_clickToMovePlayer == true) {
                        moveIntoRoom(4, getRowAndCol(x, y)[0], getRowAndCol(x, y)[1]);
                        drawRefBoardAgain();
                        drawToken();
                        drawHeros();
                        Player4_clickToMovePlayer = false;
                        setTurnToNext();
                        System.out.println(CurrentPlayer_TotalActionPoints);
                        PlayerOnSameTileSetVisible();
                        drawWinAndCheckForWin();
                    }
                    if (Player5_clickToMovePlayer == true) {
                        moveIntoRoom(5, getRowAndCol(x, y)[0], getRowAndCol(x, y)[1]);
                        drawRefBoardAgain();
                        drawToken();
                        drawHeros();
                        Player5_clickToMovePlayer = false;
                        setTurnToNext();
                        System.out.println(CurrentPlayer_TotalActionPoints);
                        PlayerOnSameTileSetVisible();
                        drawWinAndCheckForWin();
                    }
                    if (Player6_clickToMovePlayer == true) {
                        moveIntoRoom(6, getRowAndCol(x, y)[0], getRowAndCol(x, y)[1]);
                        drawRefBoardAgain();
                        drawToken();
                        drawHeros();
                        Player6_clickToMovePlayer = false;
                        setTurnToNext();
                        System.out.println(CurrentPlayer_TotalActionPoints);
                        PlayerOnSameTileSetVisible();
                        drawWinAndCheckForWin();
                    }

                if (CurrentPage == Play_Page) {
                    grid.add(verifyPlacement, 4, 1, 1, 1);
                    grid.add(rotate_btn, 4, 2, 1, 1);
                    grid.add(Player1_move_btn, 4, 3, 1, 1);
                    grid.add(Player2_move_btn, 4, 4, 1, 1);
                    grid.add(Player3_move_btn, 4, 5, 1, 1);
                    grid.add(Player4_move_btn, 4, 6, 1, 1);
                    grid.add(Player5_move_btn, 4, 7, 1, 1);
                    grid.add(Player6_move_btn, 4, 8, 1, 1);

                    grid.add(Player1PickUp_btn, 4, 4, 1, 1);
                    grid.add(Player2PickUp_btn, 4, 5, 1, 1);
                    grid.add(Player3PickUp_btn, 4, 6, 1, 1);
                    grid.add(Player4PickUp_btn, 4, 7, 1, 1);
                    grid.add(Player5PickUp_btn, 4, 8, 1, 1);
                    grid.add(Player6PickUp_btn, 4, 9, 1, 1);

                    grid.add(Give_btn, 5, 11, 1, 1);
                    grid.add(Take_btn, 6, 11, 1, 1);

                    grid.add(Player1_Giveto, 5, 12, 1, 1);
                    grid.add(Player2_Giveto, 5, 13, 1, 1);
                    grid.add(Player3_Giveto, 5, 14, 1, 1);
                    grid.add(Player4_Giveto, 5, 15, 1, 1);
                    grid.add(Player5_Giveto, 5, 16, 1, 1);
                    grid.add(Player6_Giveto, 5, 17, 1, 1);

                    grid.add(Player1_TakeFrom, 6, 12, 1, 1);
                    grid.add(Player2_TakeFrom, 6, 13, 1, 1);
                    grid.add(Player3_TakeFrom, 6, 14, 1, 1);
                    grid.add(Player4_TakeFrom, 6, 15, 1, 1);
                    grid.add(Player5_TakeFrom, 6, 16, 1, 1);
                    grid.add(Player6_TakeFrom, 6, 17, 1, 1);

                    grid.add(Player1_Giveto_lbl, 4, 12, 1, 1);
                    grid.add(Player2_Giveto_lbl, 4, 13, 1, 1);
                    grid.add(Player3_Giveto_lbl, 4, 14, 1, 1);
                    grid.add(Player4_Giveto_lbl, 4, 15, 1, 1);
                    grid.add(Player5_Giveto_lbl, 4, 16, 1, 1);
                    grid.add(Player6_Giveto_lbl, 4, 17, 1, 1);

//                  grid.add(Done_Trading, 4, 18, 1, 1);

                    grid.add(DestroyDarkRelic, 4, 19, 1, 1);

                    grid.add(wait, 4, 20, 1, 1);

                    grid.add(AttackCultist, 4, 21, 1, 1);

                    grid.add(dive,4,22,1,1);
                    showOnlyCurrentMoveBttn();

                    Player1_Giveto.setVisible(false);
                    Player2_Giveto.setVisible(false);
                    Player3_Giveto.setVisible(false);
                    Player4_Giveto.setVisible(false);
                    Player5_Giveto.setVisible(false);
                    Player6_Giveto.setVisible(false);

                    Player1_TakeFrom.setVisible(false);
                    Player2_TakeFrom.setVisible(false);
                    Player3_TakeFrom.setVisible(false);
                    Player4_TakeFrom.setVisible(false);
                    Player5_TakeFrom.setVisible(false);
                    Player6_TakeFrom.setVisible(false);
                    PlayerOnSameTileSetVisible();

                    drawWinAndCheckForWin();
                }

            }
        });
// 5/3/2021 work from here
        Player1PickUp_btn.setOnAction(event -> {
            pickUp();
            drawInventoryForPlayers();
            drawWinAndCheckForWin();
        });
        Player2PickUp_btn.setOnAction(event -> {
            pickUp();
            drawInventoryForPlayers();
            drawWinAndCheckForWin();

        });
        Player3PickUp_btn.setOnAction(event -> {
            pickUp();
            drawInventoryForPlayers();
            drawWinAndCheckForWin();
        });
        Player4PickUp_btn.setOnAction(event -> {
            pickUp();
            drawInventoryForPlayers();
            drawWinAndCheckForWin();
        });
        Player5PickUp_btn.setOnAction(event -> {
            pickUp();
            drawInventoryForPlayers();
            drawWinAndCheckForWin();
        });
        Player6PickUp_btn.setOnAction(event -> {
            pickUp();
            drawInventoryForPlayers();
            drawWinAndCheckForWin();
        });

        DestroyDarkRelic.setOnAction(event -> {
            destroyRelic();
            drawToken();
            drawWinAndCheckForWin();
        });

        Player1_move_btn.setOnAction(event -> {
            Player1_clickToMovePlayer=true;
        });
        Player2_move_btn.setOnAction(event -> {
            Player2_clickToMovePlayer=true;
        });
        Player3_move_btn.setOnAction(event -> {
            Player3_clickToMovePlayer=true;
        });
        Player4_move_btn.setOnAction(event -> {
            Player4_clickToMovePlayer=true;
        });
        Player5_move_btn.setOnAction(event -> {
            Player5_clickToMovePlayer=true;
        });
        Player6_move_btn.setOnAction(event -> {
            Player6_clickToMovePlayer=true;
        });
        if(CurrentPlayer_Turn>=1) {
            Give_btn.setOnAction(event1 -> {
                if(CurrentPlayer_TotalActionPoints>=1) {
                    for (int i = 0; i < arrayList_ItemIndexs.size(); i++) {
                        if(arrayList_ItemIndexs.get(i)!=null) {
                            tradeTo(CurrentPlayer_Turn, playerTrade, arrayList_ItemIndexs.get(i));
                            if(checkIfListContainsAnyDiffValueOtherThanNull(arrayList_ItemIndexs))
                            {
                                CurrentPlayer_TotalActionPoints--;
                            }
                            arrayList_ItemIndexs.remove(i);
                            playerTrade = 0;
                            i--;
                        }
                    }
                    drawActionPoints();
                    drawInventoryForPlayers();
                    clearCB();
                    PlayerOnSameTileSetVisible();
                    addAllInventoryItemsToTakeFromCB();
                    addAllInventoryItemsToGiveToCB();
                    drawWinAndCheckForWin();
                    setTurnToNext();
                }
            });
            Take_btn.setOnAction(event1 -> {
                if(CurrentPlayer_TotalActionPoints>=1)
                {
                    for (int i = 0; i < arrayList_ItemIndexs.size(); i++) {
                        if(arrayList_ItemIndexs.get(i)!=null) {
                            tradeTo(playerTrade, CurrentPlayer_Turn, arrayList_ItemIndexs.get(i));
                            if(arrayList_ItemIndexs.size()>0 && checkIfListContainsAnyDiffValueOtherThanNull(arrayList_ItemIndexs))
                            {
                                CurrentPlayer_TotalActionPoints--;
                            }
                            arrayList_ItemIndexs.remove(i);
                            playerTrade=0;
                            i--;
                        }
                    }
                    drawActionPoints();
                    drawInventoryForPlayers();
                    clearCB();
                    PlayerOnSameTileSetVisible();
                    addAllInventoryItemsToTakeFromCB();
                    addAllInventoryItemsToGiveToCB();
                    drawWinAndCheckForWin();
                    setTurnToNext();
                }
            });
            Player1_Giveto.setOnAction(event -> {
                arrayList_ItemIndexs.add(Player1_Giveto.getSelectionModel().getSelectedItem());
                playerTrade = 1;
                addAllInventoryItemsToGiveToCB();
                addAllInventoryItemsToTakeFromCB();
            });
            Player2_Giveto.setOnAction(event -> {
                arrayList_ItemIndexs.add(Player2_Giveto.getSelectionModel().getSelectedItem());
                playerTrade = 2;
                addAllInventoryItemsToGiveToCB();
                addAllInventoryItemsToTakeFromCB();
            });
            Player3_Giveto.setOnAction(event -> {
                arrayList_ItemIndexs.add(Player3_Giveto.getSelectionModel().getSelectedItem());
                playerTrade = 3;
                addAllInventoryItemsToGiveToCB();
                addAllInventoryItemsToTakeFromCB();
            });
            Player4_Giveto.setOnAction(event -> {
                arrayList_ItemIndexs.add(Player4_Giveto.getSelectionModel().getSelectedItem());
                playerTrade = 4;
                addAllInventoryItemsToGiveToCB();
                addAllInventoryItemsToTakeFromCB();
            });
            Player5_Giveto.setOnAction(event -> {
                arrayList_ItemIndexs.add(Player5_Giveto.getSelectionModel().getSelectedItem());
                playerTrade = 5;
                addAllInventoryItemsToGiveToCB();
                addAllInventoryItemsToTakeFromCB();
            });
            Player6_Giveto.setOnAction(event -> {
                arrayList_ItemIndexs.add(Player6_Giveto.getSelectionModel().getSelectedItem());
                playerTrade = 6;
                addAllInventoryItemsToGiveToCB();
                addAllInventoryItemsToTakeFromCB();
            });

            Player1_TakeFrom.setOnAction(event -> {
                arrayList_ItemIndexs.add(Player1_TakeFrom.getSelectionModel().getSelectedItem());
                playerTrade = 1;
                addAllInventoryItemsToGiveToCB();
                addAllInventoryItemsToTakeFromCB();
                drawWinAndCheckForWin();
            });
            Player2_TakeFrom.setOnAction(event -> {
                arrayList_ItemIndexs.add(Player2_TakeFrom.getSelectionModel().getSelectedItem());
                playerTrade = 2;
                addAllInventoryItemsToGiveToCB();
                addAllInventoryItemsToTakeFromCB();
                drawWinAndCheckForWin();
            });
            Player3_TakeFrom.setOnAction(event -> {
                arrayList_ItemIndexs.add(Player3_TakeFrom.getSelectionModel().getSelectedItem());
                playerTrade = 3;
                addAllInventoryItemsToGiveToCB();
                addAllInventoryItemsToTakeFromCB();
                drawWinAndCheckForWin();
            });
            Player4_TakeFrom.setOnAction(event -> {
                arrayList_ItemIndexs.add(Player4_TakeFrom.getSelectionModel().getSelectedItem());
                playerTrade = 4;
                addAllInventoryItemsToGiveToCB();
                addAllInventoryItemsToTakeFromCB();
                drawWinAndCheckForWin();
            });
            Player5_TakeFrom.setOnAction(event -> {
                arrayList_ItemIndexs.add(Player5_TakeFrom.getSelectionModel().getSelectedItem());
                playerTrade = 5;
                addAllInventoryItemsToGiveToCB();
                addAllInventoryItemsToTakeFromCB();
                drawWinAndCheckForWin();
            });
            Player6_TakeFrom.setOnAction(event -> {
                arrayList_ItemIndexs.add(Player6_TakeFrom.getSelectionModel().getSelectedItem());
                playerTrade = 6;
                addAllInventoryItemsToGiveToCB();
                addAllInventoryItemsToTakeFromCB();
                drawWinAndCheckForWin();
            });
            Done_Trading.setOnAction(event -> {
                for (int i = 0; i < arrayList_ItemIndexs.size(); i++) {
                    arrayList_ItemIndexs.remove(i);
                    i--;
                }
                CurrentPlayer_TotalActionPoints -= 1;
                setTurnToNext();
                drawWinAndCheckForWin();
            });
        }

        Player1Class_CB.setOnAction(event -> {
            Player1_Class = Player1Class_CB.getSelectionModel().getSelectedItem();
            drawPlayerClasses(gc, 1, Player1_Class);
            System.out.println("File:"+Player1_Class+"_Piece.png");
            Player1_Piece_Image = new Image("File:"+Player1_Class+"_Piece.png",OutlineWidth/4,OutlineHeight/4,false,false);
            if (NumOfPlayers == 1) {
                if (Player1_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                }
            }
            if (NumOfPlayers == 2) {
                if (Player1_Class != "" && Player2_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                }
            }
            if (NumOfPlayers == 3) {
                if (Player1_Class != "" && Player2_Class != "" && Player3_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                    Player3_Position[0] = 4;
                    Player3_Position[1] = 4;
                }
            }
            if (NumOfPlayers == 4) {
                if (Player1_Class != "" && Player2_Class != "" && Player3_Class != "" && Player4_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                    Player3_Position[0] = 4;
                    Player3_Position[1] = 4;
                    Player4_Position[0] = 4;
                    Player4_Position[1] = 4;
                }
            }
            if (NumOfPlayers == 5) {
                if (Player1_Class != "" && Player2_Class != "" && Player3_Class != "" && Player4_Class != "" && Player5_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                    Player3_Position[0] = 4;
                    Player3_Position[1] = 4;
                    Player4_Position[0] = 4;
                    Player4_Position[1] = 4;
                    Player5_Position[0] = 4;
                    Player5_Position[1] = 4;
                }}
            if (NumOfPlayers == 6) {
                if (Player1_Class != "" && Player2_Class != "" && Player3_Class != "" && Player4_Class != "" && Player5_Class != "" && Player6_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                    Player3_Position[0] = 4;
                    Player3_Position[1] = 4;
                    Player4_Position[0] = 4;
                    Player4_Position[1] = 4;
                    Player5_Position[0] = 4;
                    Player5_Position[1] = 4;
                    Player6_Position[0] = 4;
                    Player6_Position[1] = 4;
                }}
        });
        Player2Class_CB.setOnAction(event -> {
            Player2_Class = Player2Class_CB.getSelectionModel().getSelectedItem();
            drawPlayerClasses(gc, 2, Player2_Class);
            Player2_Piece_Image = new Image("File:"+Player2_Class+"_Piece.png",OutlineWidth/4,OutlineHeight/4,false,false);
            if (NumOfPlayers == 1) {
                if (Player1_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                }

            }
            if (NumOfPlayers == 2) {
                if (Player1_Class != "" && Player2_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                }
            }
            if (NumOfPlayers == 3) {
                if (Player1_Class != "" && Player2_Class != "" && Player3_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                    Player3_Position[0] = 4;
                    Player3_Position[1] = 4;
                }
            }
            if (NumOfPlayers == 4) {
                if (Player1_Class != "" && Player2_Class != "" && Player3_Class != "" && Player4_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                    Player3_Position[0] = 4;
                    Player3_Position[1] = 4;
                    Player4_Position[0] = 4;
                    Player4_Position[1] = 4;
                }
            }
            if (NumOfPlayers == 5) {
                if (Player1_Class != "" && Player2_Class != "" && Player3_Class != "" && Player4_Class != "" && Player5_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                    Player3_Position[0] = 4;
                    Player3_Position[1] = 4;
                    Player4_Position[0] = 4;
                    Player4_Position[1] = 4;
                    Player5_Position[0] = 4;
                    Player5_Position[1] = 4;
                }}
            if (NumOfPlayers == 6) {
                if (Player1_Class != "" && Player2_Class != "" && Player3_Class != "" && Player4_Class != "" && Player5_Class != "" && Player6_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                    Player3_Position[0] = 4;
                    Player3_Position[1] = 4;
                    Player4_Position[0] = 4;
                    Player4_Position[1] = 4;
                    Player5_Position[0] = 4;
                    Player5_Position[1] = 4;
                    Player6_Position[0] = 4;
                    Player6_Position[1] = 4;
                }}
        });
        Player3Class_CB.setOnAction(event -> {
            Player3_Class = Player3Class_CB.getSelectionModel().getSelectedItem();
            drawPlayerClasses(gc, 3, Player3_Class);
            Player3_Piece_Image = new Image("File:"+Player3_Class+"_Piece.png",OutlineWidth/4,OutlineHeight/4,false,false);
            if (NumOfPlayers == 1) {
                if (Player1_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                }
            }
            if (NumOfPlayers == 2) {
                if (Player1_Class != "" && Player2_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                }
            }
            if (NumOfPlayers == 3) {
                if (Player1_Class != "" && Player2_Class != "" && Player3_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                    Player3_Position[0] = 4;
                    Player3_Position[1] = 4;
                }
            }
            if (NumOfPlayers == 4) {
                if (Player1_Class != "" && Player2_Class != "" && Player3_Class != "" && Player4_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                    Player3_Position[0] = 4;
                    Player3_Position[1] = 4;
                    Player4_Position[0] = 4;
                    Player4_Position[1] = 4;
                }
            }
            if (NumOfPlayers == 5) {
                if (Player1_Class != "" && Player2_Class != "" && Player3_Class != "" && Player4_Class != "" && Player5_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                    Player3_Position[0] = 4;
                    Player3_Position[1] = 4;
                    Player4_Position[0] = 4;
                    Player4_Position[1] = 4;
                    Player5_Position[0] = 4;
                    Player5_Position[1] = 4;
                }}
            if (NumOfPlayers == 6) {
                if (Player1_Class != "" && Player2_Class != "" && Player3_Class != "" && Player4_Class != "" && Player5_Class != "" && Player6_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                    Player3_Position[0] = 4;
                    Player3_Position[1] = 4;
                    Player4_Position[0] = 4;
                    Player4_Position[1] = 4;
                    Player5_Position[0] = 4;
                    Player5_Position[1] = 4;
                    Player6_Position[0] = 4;
                    Player6_Position[1] = 4;
                }}
        });
        Player4Class_CB.setOnAction(event -> {
            Player4_Class = Player4Class_CB.getSelectionModel().getSelectedItem();
            drawPlayerClasses(gc, 4, Player4_Class);
            Player4_Piece_Image = new Image("File:"+Player4_Class+"_Piece.png",OutlineWidth/4,OutlineHeight/4,false,false);
            if (NumOfPlayers == 1) {
                if (Player1_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                }
            }
            if (NumOfPlayers == 2) {
                if (Player1_Class != "" && Player2_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                }
            }
            if (NumOfPlayers == 3) {
                if (Player1_Class != "" && Player2_Class != "" && Player3_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                    Player3_Position[0] = 4;
                    Player3_Position[1] = 4;
                }
            }
            if (NumOfPlayers == 4) {
                if (Player1_Class != "" && Player2_Class != "" && Player3_Class != "" && Player4_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                    Player3_Position[0] = 4;
                    Player3_Position[1] = 4;
                    Player4_Position[0] = 4;
                    Player4_Position[1] = 4;
                }
            }
            if (NumOfPlayers == 5) {
                if (Player1_Class != "" && Player2_Class != "" && Player3_Class != "" && Player4_Class != "" && Player5_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                    Player3_Position[0] = 4;
                    Player3_Position[1] = 4;
                    Player4_Position[0] = 4;
                    Player4_Position[1] = 4;
                    Player5_Position[0] = 4;
                    Player5_Position[1] = 4;
                }}
            if (NumOfPlayers == 6) {
                if (Player1_Class != "" && Player2_Class != "" && Player3_Class != "" && Player4_Class != "" && Player5_Class != "" && Player6_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                    Player3_Position[0] = 4;
                    Player3_Position[1] = 4;
                    Player4_Position[0] = 4;
                    Player4_Position[1] = 4;
                    Player5_Position[0] = 4;
                    Player5_Position[1] = 4;
                    Player6_Position[0] = 4;
                    Player6_Position[1] = 4;
                }}
        });
        Player5Class_CB.setOnAction(event -> {
            Player5_Class = Player5Class_CB.getSelectionModel().getSelectedItem();
            drawPlayerClasses(gc, 5, Player5_Class);
            Player5_Piece_Image = new Image("File:"+Player5_Class+"_Piece.png",OutlineWidth/4,OutlineHeight/4,false,false);
            if (NumOfPlayers == 1) {
                if (Player1_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                }

            }
            if (NumOfPlayers == 2) {
                if (Player1_Class != "" && Player2_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                }
            }
            if (NumOfPlayers == 3) {
                if (Player1_Class != "" && Player2_Class != "" && Player3_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                    Player3_Position[0] = 4;
                    Player3_Position[1] = 4;
                }
            }
            if (NumOfPlayers == 4) {
                if (Player1_Class != "" && Player2_Class != "" && Player3_Class != "" && Player4_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                    Player3_Position[0] = 4;
                    Player3_Position[1] = 4;
                    Player4_Position[0] = 4;
                    Player4_Position[1] = 4;
                }
            }
            if (NumOfPlayers == 5) {
                if (Player1_Class != "" && Player2_Class != "" && Player3_Class != "" && Player4_Class != "" && Player5_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                    Player3_Position[0] = 4;
                    Player3_Position[1] = 4;
                    Player4_Position[0] = 4;
                    Player4_Position[1] = 4;
                    Player5_Position[0] = 4;
                    Player5_Position[1] = 4;
                }}
            if (NumOfPlayers == 6) {
                if (Player1_Class != "" && Player2_Class != "" && Player3_Class != "" && Player4_Class != "" && Player5_Class != "" && Player6_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                    Player3_Position[0] = 4;
                    Player3_Position[1] = 4;
                    Player4_Position[0] = 4;
                    Player4_Position[1] = 4;
                    Player5_Position[0] = 4;
                    Player5_Position[1] = 4;
                    Player6_Position[0] = 4;
                    Player6_Position[1] = 4;
                }}
        });
        Player6Class_CB.setOnAction(event -> {
            Player6_Class = Player6Class_CB.getSelectionModel().getSelectedItem();
            drawPlayerClasses(gc, 6, Player6_Class);
            Player6_Piece_Image = new Image("File:"+Player6_Class+"_Piece.png",OutlineWidth/4,OutlineHeight/4,false,false);
            if (NumOfPlayers == 1) {
                if (Player1_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                }

            }
            if (NumOfPlayers == 2) {
                if (Player1_Class != "" && Player2_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                }
            }
            if (NumOfPlayers == 3) {
                if (Player1_Class != "" && Player2_Class != "" && Player3_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                    Player3_Position[0] = 4;
                    Player3_Position[1] = 4;
                }
            }
            if (NumOfPlayers == 4) {
                if (Player1_Class != "" && Player2_Class != "" && Player3_Class != "" && Player4_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                    Player3_Position[0] = 4;
                    Player3_Position[1] = 4;
                    Player4_Position[0] = 4;
                    Player4_Position[1] = 4;
                }
            }
            if (NumOfPlayers == 5) {
                if (Player1_Class != "" && Player2_Class != "" && Player3_Class != "" && Player4_Class != "" && Player5_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                    Player3_Position[0] = 4;
                    Player3_Position[1] = 4;
                    Player4_Position[0] = 4;
                    Player4_Position[1] = 4;
                    Player5_Position[0] = 4;
                    Player5_Position[1] = 4;
                }}
            if (NumOfPlayers == 6) {
                if (Player1_Class != "" && Player2_Class != "" && Player3_Class != "" && Player4_Class != "" && Player5_Class != "" && Player6_Class != "") {
                    playersDoneSelecting = true;
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                    Player3_Position[0] = 4;
                    Player3_Position[1] = 4;
                    Player4_Position[0] = 4;
                    Player4_Position[1] = 4;
                    Player5_Position[0] = 4;
                    Player5_Position[1] = 4;
                    Player6_Position[0] = 4;
                    Player6_Position[1] = 4;
                }}
        });

        AttackCultist.setOnAction(event -> {
            attackCultist();
            drawWinAndCheckForWin();
        });

        wait.setOnAction(event -> {
            CurrentPlayer_TotalActionPoints=0;
            setTurnToNext();
            drawActionPoints();
            drawInventoryForPlayers();
            clearCB();
            PlayerOnSameTileSetVisible();
            addAllInventoryItemsToTakeFromCB();
            addAllInventoryItemsToGiveToCB();
            drawWinAndCheckForWin();
        });
        dive.setOnAction(event -> {
            checkIfPLayerIsInVoidAndDive();
            drawRefBoardAgain();
        });

        drawWinAndCheckForWin();
        Scene scene = new Scene(group);
        canvas.requestFocus();
        primaryStage.setScene(scene);
        // draw(canvas.getGraphicsContext2D());
        primaryStage.show();
    }
    public void setVisibilityBasedOnPhase()
    {
        if(Phase==1)
        {
            verifyPlacement.setVisible(true);
            rotate_btn.setVisible(true);
            PlayerOnSameTileSetVisible();
            Give_btn.setVisible(true);
            Take_btn.setVisible(true);
            showOnlyCurrentMoveBttn();
            DestroyDarkRelic.setVisible(true);
            wait.setVisible(true);
            AttackCultist.setVisible(true);
            dive.setVisible(true);
        }
        else
            {
                verifyPlacement.setVisible(false);
                rotate_btn.setVisible(false);
                Player1_Giveto.setVisible(false);
                Player2_Giveto.setVisible(false);
                Player3_Giveto.setVisible(false);
                Player4_Giveto.setVisible(false);
                Player5_Giveto.setVisible(false);
                Player6_Giveto.setVisible(false);
                Give_btn.setVisible(false);
                Take_btn.setVisible(false);
                Player1_move_btn.setVisible(false);
                Player2_move_btn.setVisible(false);
                Player3_move_btn.setVisible(false);
                Player4_move_btn.setVisible(false);
                Player5_move_btn.setVisible(false);
                Player6_move_btn.setVisible(false);
                Player1PickUp_btn.setVisible(false);
                Player2PickUp_btn.setVisible(false);
                Player3PickUp_btn.setVisible(false);
                Player4PickUp_btn.setVisible(false);
                Player5PickUp_btn.setVisible(false);
                Player6PickUp_btn.setVisible(false);
                DestroyDarkRelic.setVisible(false);
                wait.setVisible(false);
                AttackCultist.setVisible(false);
                dive.setVisible(false);
            }
    }
    public boolean checkIfListContainsAnyDiffValueOtherThanNull(ArrayList<Integer> list)
    {
        for (int i = 0; i <list.size()-1; i++) {
            if(list.get(i)!=null && list.get(i+1)!=null && list.get(i)!=list.get(i+1))
            {
                    return false;
            }
        }
        return true;
    }
    public void checkIfPLayerIsInVoidAndDive()
    {
        if(CurrentPlayer_TotalActionPoints>=1) {
            if (CurrentPlayer_Turn == 1) {
                if (refGrid[Player1_Position[0]][Player1_Position[1]] == null) {
                    Player1_Position[0] = 4;
                    Player1_Position[1] = 4;
                    CurrentPlayer_TotalActionPoints--;
                    P1Down = true;
                }
            }
            if (CurrentPlayer_Turn == 2) {
                if (refGrid[Player2_Position[0]][Player2_Position[1]] == null) {
                    Player2_Position[0] = 4;
                    Player2_Position[1] = 4;
                    CurrentPlayer_TotalActionPoints--;
                    P2Down = true;
                }
            }
            if (CurrentPlayer_Turn == 3) {
                if (refGrid[Player3_Position[0]][Player3_Position[1]] == null) {
                    Player3_Position[0] = 4;
                    Player3_Position[1] = 4;
                    CurrentPlayer_TotalActionPoints--;
                    P3Down = true;
                }
            }
            if (CurrentPlayer_Turn == 4) {
                if (refGrid[Player4_Position[0]][Player4_Position[1]] == null) {
                    Player4_Position[0] = 4;
                    Player4_Position[1] = 4;
                    CurrentPlayer_TotalActionPoints--;
                    P4Down = true;
                }
            }
            if (CurrentPlayer_Turn == 5) {
                if (refGrid[Player5_Position[0]][Player5_Position[1]] == null) {
                    Player5_Position[0] = 4;
                    Player5_Position[1] = 4;
                    CurrentPlayer_TotalActionPoints--;
                    P5Down = true;
                }
            }
            if (CurrentPlayer_Turn == 6) {
                if (refGrid[Player6_Position[0]][Player6_Position[1]] == null) {
                    Player6_Position[0] = 4;
                    Player6_Position[1] = 4;
                    CurrentPlayer_TotalActionPoints--;
                    P6Down = true;
                }
            }
        }
        drawActionPoints();
        drawRefBoardAgain();
        drawHeros();
    }
    public void drawWinAndCheckForWin()
    {
        boolean P1 = false;
        boolean P2 = false;
        boolean P3 = false;
        boolean P4 = false;
        boolean P5 = false;
        boolean P6 = false;
        boolean FireOFEIDOLONCHECK = false;
        if(Player1Inventory.contains("Fireball") ||Player2Inventory.contains("Fireball") ||Player3Inventory.contains("Fireball") ||Player4Inventory.contains("Fireball") ||Player5Inventory.contains("Fireball") ||Player6Inventory.contains("Fireball") )
        {
            FireOFEIDOLONCHECK=true;
        }
        if(NumOfPlayers>=1)
        {
            if(Player1_Position[0]==4 && Player1_Position[1]==4)
            {
                P1=true;
            }
        }
        if(NumOfPlayers>=2)
        {
            if(Player2_Position[0]==4 && Player2_Position[1]==4)
            {
                P2=true;
            }
        }
        if(NumOfPlayers>=3)
        {
            if(Player3_Position[0]==4 && Player3_Position[1]==4)
            {
                P3=true;
            }
        }
        if(NumOfPlayers>=4)
        {
            if(Player4_Position[0]==4 && Player4_Position[1]==4)
            {
                P4=true;
            }
        }
        if(NumOfPlayers>=5)
        {
            if(Player5_Position[0]==5 && Player5_Position[1]==4)
            {
                P5=true;
            }
        }
        if(NumOfPlayers==6)
        {
            if(Player6_Position[0]==4 && Player6_Position[1]==4)
            {
                P6=true;
            }
        }
        if(FireOFEIDOLONCHECK && NumOfPlayers==1 && P1)
        {
            gc.setFill(Color.BLACK);
            gc.fillRect(0,0,1000,1000);
            gc.strokeText("You win!!",canvasWidth/2,canvasHeight/2);
        }
        if(FireOFEIDOLONCHECK &&NumOfPlayers==2 && P1 && P2)
        {
            gc.setFill(Color.BLACK);
            gc.fillRect(0,0,1000,1000);
            gc.strokeText("You win!!",canvasWidth/2,canvasHeight/2);
        }
        if(FireOFEIDOLONCHECK &&NumOfPlayers==3 && P1 && P2 && P3)
        {
            gc.setFill(Color.BLACK);
            gc.fillRect(0,0,1000,1000);
            gc.strokeText("You win!!",canvasWidth/2,canvasHeight/2);
        }
        if(FireOFEIDOLONCHECK && NumOfPlayers==4 && P1 && P2 && P3 && P4)
        {
            gc.setFill(Color.BLACK);
            gc.fillRect(0,0,1000,1000);
            gc.strokeText("You win!!",canvasWidth/2,canvasHeight/2);
        }
        if(FireOFEIDOLONCHECK && NumOfPlayers==5 && P1 && P2 && P3 && P4 && P5)
        {
            gc.setFill(Color.BLACK);
            gc.fillRect(0,0,1000,1000);
            gc.strokeText("You win!!",canvasWidth/2,canvasHeight/2);
        }
        if(FireOFEIDOLONCHECK && NumOfPlayers==6 && P1 && P2 && P3 && P4 && P5 && P6)
        {
            gc.setFill(Color.BLACK);
            gc.fillRect(0,0,1000,1000);
            gc.strokeText("You win!!",canvasWidth/2,canvasHeight/2);
        }

    }
    public void attackCultist()
    {
        if(CurrentPlayer_TotalActionPoints>=1)
        {
            if(CurrentPlayer_Turn==1){
                checkForCultistAndGetTileIfTrue(Player1_Position[0],Player1_Position[1]);
                CurrentPlayer_TotalActionPoints--;
            }
            if(CurrentPlayer_Turn==2){
                checkForCultistAndGetTileIfTrue(Player2_Position[0],Player2_Position[1]);
                CurrentPlayer_TotalActionPoints--;
            }
            if(CurrentPlayer_Turn==3){
                checkForCultistAndGetTileIfTrue(Player3_Position[0],Player3_Position[1]);
                CurrentPlayer_TotalActionPoints--;
            }
            if(CurrentPlayer_Turn==4){
                checkForCultistAndGetTileIfTrue(Player4_Position[0],Player4_Position[1]);
                CurrentPlayer_TotalActionPoints--;
            }
            if(CurrentPlayer_Turn==5){
                checkForCultistAndGetTileIfTrue(Player5_Position[0],Player5_Position[1]);
                CurrentPlayer_TotalActionPoints--;

            }
            if(CurrentPlayer_Turn==6){
                checkForCultistAndGetTileIfTrue(Player6_Position[0],Player6_Position[1]);
                CurrentPlayer_TotalActionPoints--;
            }
            drawRefBoardAgain();
            drawCultist();
            drawHeros();
            drawActionPoints();
        }
    }
    public void checkForCultistAndGetTileIfTrue(int r, int c)
    {
        if(refGrid[r][c]=="AcidJets"        && AcidJets_CultistTokenCount>=1)
        {AcidJets_CultistTokenCount--;}
        if(refGrid[r][c]=="ArrowTraps"       && ArrowTrap_CultistTokenCount>=1)
        {ArrowTrap_CultistTokenCount--;}
        if(refGrid[r][c]=="VoraxsKnowledge"       && BlueEvent_CultistTokenCount>=1)
        { BlueEvent_CultistTokenCount--;}
        if(refGrid[r][c]=="DarkSlime"       && DarkSlime_CultistTokenCount>=1)
         {DarkSlime_CultistTokenCount--;}
        if(refGrid[r][c]=="DenOfSnakes"     && DenOfSnakes_CultistTokenCount>=1)
        { DarkSlime_CultistTokenCount--;}
        if(refGrid[r][c]=="Dragonling"      && Dragonling_CultistTokenCount>=1)
         { Dragonling_CultistTokenCount--;}
        if(refGrid[r][c]=="FelKnight"       && FelKnight_CultistTokenCount>=1)
        { FelKnight_CultistTokenCount--;}
        if(refGrid[r][c]=="FloatingStones"  && FloatingStones_CultistTokenCount>=1)
        { FloatingStones_CultistTokenCount--;}
        if(refGrid[r][c]=="VoraxsFocus"      && GreenEvent_CultistTokenCount>=1)
        { GreenEvent_CultistTokenCount--;}
        if(refGrid[r][c]=="HallOfIllusion"  && HallOfIllusions_CultistTokenCount>=1)
        { HallOfIllusions_CultistTokenCount--;}
        if(refGrid[r][c]=="LaughingShadow"  && LaughingShadow_CultistTokenCount>=1)
        {LaughingShadow_CultistTokenCount--;}
        if(refGrid[r][c]=="LavaLake"        && LavaLake_CultistTokenCount>=1)
        { LavaLake_CultistTokenCount--;}
        if(refGrid[r][c]=="MimicChest"      && MimicChest_CultistTokenCount>=1)
        { MimicChest_CultistTokenCount--;}
        if(refGrid[r][c]=="MindEater"       && MindEater_CultistTokenCount>=1)
        { MindEater_CultistTokenCount--;}
        if(refGrid[r][c]=="Minotaur"        && Minotaur_CultistTokenCount>=1)
        { Minotaur_CultistTokenCount--;}
        if(refGrid[r][c]=="OgreBrute"       && OgreBrute_CultistTokenCount>=1)
        { OgreBrute_CultistTokenCount--;}
        if(refGrid[r][c]=="ParadoxPuzzle"   && ParadoxPuzzle_CultistTokenCount>=1)
        { ParadoxPuzzle_CultistTokenCount--;}
        if(refGrid[r][c]=="PendulumBlades"  && PendulumBlades_CultistTokenCount>=1)
        { PendulumBlades_CultistTokenCount--;}
        if(refGrid[r][c]=="Psychomancer"    && Psychomancer_CultistTokenCount>=1)
        { Psychomancer_CultistTokenCount--;}
        if(refGrid[r][c]=="VoraxsHeart"        && RedEvent_CultistTokenCount>=1)
        {RedEvent_CultistTokenCount--;}
        if(refGrid[r][c]=="SkeletalGuards"    && SkeletalGuards_CultistTokenCount>=1)
        { SkeletalGuards_CultistTokenCount--;}
        if(refGrid[r][c]=="SphynxsRiddle"     && SphynxsRiddle_CultistTokenCount>=1)
        { SphynxsRiddle_CultistTokenCount--;}
        if(refGrid[r][c]=="SpikedPit"         && SpikedPit_CultistTokenCount>=1)
        { SpikedPit_CultistTokenCount--;}
        if(refGrid[r][c]=="VoraciousPlant"    && VoraciousPlant_CultistTokenCount>=1)
        { VoraciousPlant_CultistTokenCount--;}
    }
    public void CheckFor2CultistTokensAndRemoveTile()
    {
        if(AcidJets_CultistTokenCount == 2)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="AcidJets") {
                        AcidJets_PowerToken = "";
                        refGrid[r][c]=null;
                        AlreadyOpenedRitualCardStack.remove(AlreadyOpenedRitualCardStack.indexOf("AcidJets_Ritual"));
                    }
                }
            }
        }
        if(ArrowTrap_CultistTokenCount == 2)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="ArrowTraps") {
                        ArrowTraps_PowerToken = "";
                        refGrid[r][c]=null;
                        AlreadyOpenedRitualCardStack.remove(AlreadyOpenedRitualCardStack.indexOf("ArrowTrap_Ritual"));
                    }
                }
            }
        }
        if(BlueEvent_CultistTokenCount == 2)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="VoraxsKnowledge") {
                        VoraxsKnowledge_DarkRelic = "";
                        refGrid[r][c]=null;
                        AlreadyOpenedRitualCardStack.remove(AlreadyOpenedRitualCardStack.indexOf("BlueEvent_Ritual"));
                    }
                }
            }
        }
        if(DarkSlime_CultistTokenCount == 2)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="DarkSlime") {
                        DarkSlime_PowerToken = "";
                        refGrid[r][c]=null;
                        AlreadyOpenedRitualCardStack.remove(AlreadyOpenedRitualCardStack.indexOf("DarkSlime_Ritual"));
                    }
                }
            }
        }
        if(DenOfSnakes_CultistTokenCount == 2)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="DenOfSnakes") {
                        DenOfSnakes_PowerToken = "";
                        refGrid[r][c]=null;
                        AlreadyOpenedRitualCardStack.remove(AlreadyOpenedRitualCardStack.indexOf("DenOfSnakes_Ritual"));
                    }
                }
            }
        }
        if(Dragonling_CultistTokenCount == 2)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="Dragonling") {
                        Dragonling_PowerToken = "";
                        refGrid[r][c]=null;
                        AlreadyOpenedRitualCardStack.remove(AlreadyOpenedRitualCardStack.indexOf("Dragonling_Ritual"));
                    }
                }
            }
        }
        if(FelKnight_CultistTokenCount == 2)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="FelKnight") {
                        FelKnight_PowerToken = "";
                        refGrid[r][c]=null;
                        AlreadyOpenedRitualCardStack.remove(AlreadyOpenedRitualCardStack.indexOf("FelKnight_Ritual"));
                    }
                }
            }
        }
        if(FloatingStones_CultistTokenCount == 2)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="FloatingStones") {
                        FloatingStones_PowerToken = "";
                        refGrid[r][c]=null;
                        AlreadyOpenedRitualCardStack.remove(AlreadyOpenedRitualCardStack.indexOf("FloatingStones_Ritual"));
                    }
                }
            }
        }

        if(GreenEvent_CultistTokenCount == 2)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="VoraxsFocus") {
                        VoraxsFocus_DarkRelic = "";
                        refGrid[r][c]=null;
                        AlreadyOpenedRitualCardStack.remove(AlreadyOpenedRitualCardStack.indexOf("GreenEvent_Ritual"));
                    }
                }
            }
        }
        if(HallOfIllusions_CultistTokenCount == 2)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="HallOfIllusion") {
                        HallOfIllusion_PowerToken = "";
                        refGrid[r][c]=null;
                        AlreadyOpenedRitualCardStack.remove(AlreadyOpenedRitualCardStack.indexOf("HallOfIllusion"));
                    }
                }
            }
        }
        if(LaughingShadow_CultistTokenCount == 2)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="LaughingShadow") {
                        LaughingShadow_PowerToken = "";
                        refGrid[r][c]=null;
                        AlreadyOpenedRitualCardStack.remove(AlreadyOpenedRitualCardStack.indexOf("LaughingShadow_Ritual"));
                    }
                }
            }
        }
        if(LavaLake_CultistTokenCount == 2)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="LavaLake") {
                        LavaLake_PowerToken = "";
                        refGrid[r][c]=null;
                        AlreadyOpenedRitualCardStack.remove(AlreadyOpenedRitualCardStack.indexOf("LavaLake_Ritual"));
                    }
                }
            }
        }
        if(MimicChest_CultistTokenCount == 2)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="MimicChest") {
                        MimicChest_PowerToken = "";
                        refGrid[r][c]=null;
                        AlreadyOpenedRitualCardStack.remove(AlreadyOpenedRitualCardStack.indexOf("MimicChest_Ritual"));
                    }
                }
            }
        }
        if(MindEater_CultistTokenCount == 2)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="MindEater") {
                        MindEater_PowerToken = "";
                        refGrid[r][c]=null;
                        AlreadyOpenedRitualCardStack.remove(AlreadyOpenedRitualCardStack.indexOf("MindEater_Ritual"));
                    }
                }
            }
        }
        if(Minotaur_CultistTokenCount == 2)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="Minotaur") {
                        Minotaur_PowerToken = "";
                        refGrid[r][c]=null;
                        AlreadyOpenedRitualCardStack.remove(AlreadyOpenedRitualCardStack.indexOf("Minotaur_Ritual"));
                    }
                }
            }
        }
        if(OgreBrute_CultistTokenCount == 2)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="OgreBrute") {
                        OgreBrute_PowerToken = "";
                        refGrid[r][c]=null;
                        AlreadyOpenedRitualCardStack.remove(AlreadyOpenedRitualCardStack.indexOf("OgreBrute_Ritual"));
                    }
                }
            }
        }
        if(ParadoxPuzzle_CultistTokenCount == 2)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="ParadoxPuzzle") {
                        ParadoxPuzzle_PowerToken = "";
                        refGrid[r][c]=null;
                        AlreadyOpenedRitualCardStack.remove(AlreadyOpenedRitualCardStack.indexOf("ParadoxPuzzle_Ritual"));
                    }
                }
            }
        }
        if(PendulumBlades_CultistTokenCount == 2)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="PendulumBlades") {
                        PendulumBlades_PowerToken = "";
                        refGrid[r][c]=null;
                        AlreadyOpenedRitualCardStack.remove(AlreadyOpenedRitualCardStack.indexOf("PendulumBlades_Ritual"));
                    }
                }
            }
        }
        if(Psychomancer_CultistTokenCount == 2)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="Psychomancer") {
                        Psychomancer_PowerToken = "";
                        refGrid[r][c]=null;
                        AlreadyOpenedRitualCardStack.remove(AlreadyOpenedRitualCardStack.indexOf("Psychomancer_Ritual"));
                    }
                }
            }
        }
        if(RedEvent_CultistTokenCount == 2)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="VoraxsHeart") {
                        VoraxsHeart_DarkRelic = "";
                        refGrid[r][c]=null;
                        AlreadyOpenedRitualCardStack.remove(AlreadyOpenedRitualCardStack.indexOf("RedEvent_Ritual"));
                    }
                }
            }
        }
        if(SkeletalGuards_CultistTokenCount == 2)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="SkeletalGuards") {
                        SkeletalGuards_PowerToken = "";
                        refGrid[r][c]=null;
                        AlreadyOpenedRitualCardStack.remove(AlreadyOpenedRitualCardStack.indexOf("SkeletalGuards_Ritual"));
                    }
                }
            }
        }
        if(SphynxsRiddle_CultistTokenCount == 2)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="SphynxsRiddle") {
                        SphynxsRiddle_PowerToken = "";
                        refGrid[r][c]=null;
                        AlreadyOpenedRitualCardStack.remove(AlreadyOpenedRitualCardStack.indexOf("SphynxsRiddle_Ritual"));
                    }
                }
            }
        }
        if(SpikedPit_CultistTokenCount == 2)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="SpikedPit") {
                        SpikedPit_PowerToken = "";
                        refGrid[r][c]=null;
                        AlreadyOpenedRitualCardStack.remove(AlreadyOpenedRitualCardStack.indexOf("SpikedPit_Ritual"));
                    }
                }
            }
        }
        if(VoraciousPlant_CultistTokenCount == 2)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="VoraciousPlant") {
                        VoraciousPlant_PowerToken = "";
                        refGrid[r][c]=null;
                        AlreadyOpenedRitualCardStack.remove(AlreadyOpenedRitualCardStack.indexOf("VoraciousPlant_Ritual"));
                    }
                }
            }
        }
        drawRefBoardAgain();
        if(Phase==2) {
            drawRitualStack();
        }

        if(StackOfRitualCards.size()==0)
        {
            StackOfRitualCards.addAll(AlreadyOpenedRitualCardStack);
            for (int i = 0; i <AlreadyOpenedRitualCardStack.size() ; i++)
            {
                AlreadyOpenedRitualCardStack.remove(i);
                i--;
            }
            Collections.shuffle(StackOfRitualCards);
            ThreatIndex--;
            drawThreatLevel();
        }
    }
    public void addCultistTokenToChamberAndDrawToken(String RitualCard)
    {
        if("AcidJets_Ritual"==RitualCard)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="AcidJets")
                    {
                        AcidJets_CultistTokenCount++;
                        gc.drawImage(new Image("File:CultistToken.png",tileLengths,tileLengths,false,false),10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths), tileLengths / 2, tileLengths / 2);
                    }
                }
            }
        }
        if("ArrowTrap_Ritual"==RitualCard)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="ArrowTraps")
                    {
                        ArrowTrap_CultistTokenCount++;
                        gc.drawImage(new Image("File:CultistToken.png",tileLengths,tileLengths,false,false),10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths), tileLengths / 2, tileLengths / 2);
                    }
                }
            }
        }
        if("BlueEvent_Ritual"==RitualCard)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="VoraxsKnowledge")
                    {
                        BlueEvent_CultistTokenCount++;
                        gc.drawImage(new Image("File:CultistToken.png",tileLengths,tileLengths,false,false),10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths), tileLengths / 2, tileLengths / 2);
                    }
                }
            }
        }
        if("DarkSlime_Ritual"==RitualCard)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="DarkSlime")
                    {
                        DarkSlime_CultistTokenCount++;
                        gc.drawImage(new Image("File:CultistToken.png",tileLengths,tileLengths,false,false),10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths), tileLengths / 2, tileLengths / 2);
                    }
                }
            }
        }
        if("DenOfSnakes_Ritual"==RitualCard)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="DenOfSnakes")
                    {
                        DenOfSnakes_CultistTokenCount++;
                        gc.drawImage(new Image("File:CultistToken.png",tileLengths,tileLengths,false,false),10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths), tileLengths / 2, tileLengths / 2);
                    }
                }
            }
        }
        if("Dragonling_Ritual"==RitualCard)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="Dragonling")
                    {
                        Dragonling_CultistTokenCount++;
                        gc.drawImage(new Image("File:CultistToken.png",tileLengths,tileLengths,false,false),10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths), tileLengths / 2, tileLengths / 2);
                    }
                }
            }
        }
        if("FelKnight_Ritual"==RitualCard)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="FelKnight")
                    {
                        FelKnight_CultistTokenCount++;
                        gc.drawImage(new Image("File:CultistToken.png",tileLengths,tileLengths,false,false),10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths), tileLengths / 2, tileLengths / 2);
                    }
                }
            }
        }
        if("FloatingStones_Ritual"==RitualCard)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="FloatingStones")
                    {
                        FloatingStones_CultistTokenCount++;
                        gc.drawImage(new Image("File:CultistToken.png",tileLengths,tileLengths,false,false),10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths), tileLengths / 2, tileLengths / 2);
                    }
                }
            }
        }
        if("GreenEvent_Ritual"==RitualCard)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="VoraxsFocus")
                    {
                        GreenEvent_CultistTokenCount++;
                        gc.drawImage(new Image("File:CultistToken.png",tileLengths,tileLengths,false,false),10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths), tileLengths / 2, tileLengths / 2);
                    }
                }
            }
        }
        if("HallOfIllusion_Ritual"==RitualCard)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="HallOfIllusion")
                    {
                        HallOfIllusions_CultistTokenCount++;
                        gc.drawImage(new Image("File:CultistToken.png",tileLengths,tileLengths,false,false),10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths), tileLengths / 2, tileLengths / 2);
                    }
                }
            }
        }
        if("LaughingShadow_Ritual"==RitualCard)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="LaughingShadow")
                    {
                        LaughingShadow_CultistTokenCount++;
                        gc.drawImage(new Image("File:CultistToken.png",tileLengths,tileLengths,false,false),10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths), tileLengths / 2, tileLengths / 2);
                    }
                }
            }
        }
        if("LavaLake_Ritual"==RitualCard)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="LavaLake")
                    {
                        LavaLake_CultistTokenCount++;
                        gc.drawImage(new Image("File:CultistToken.png",tileLengths,tileLengths,false,false),10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths), tileLengths / 2, tileLengths / 2);
                    }
                }
            }
        }
        if("MimicChest_Ritual"==RitualCard)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="MimicChest")
                    {
                        MimicChest_CultistTokenCount++;
                        gc.drawImage(new Image("File:CultistToken.png",tileLengths,tileLengths,false,false),10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths), tileLengths / 2, tileLengths / 2);
                    }
                }
            }
        }
        if("MindEater_Ritual"==RitualCard)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="MindEater")
                    {
                        MindEater_CultistTokenCount++;
                        gc.drawImage(new Image("File:CultistToken.png",tileLengths,tileLengths,false,false),10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths), tileLengths / 2, tileLengths / 2);
                    }
                }
            }
        }
        if("Minotaur_Ritual"==RitualCard)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="Minotaur")
                    {
                        Minotaur_CultistTokenCount++;
                        gc.drawImage(new Image("File:CultistToken.png",tileLengths,tileLengths,false,false),10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths), tileLengths / 2, tileLengths / 2);
                    }
                }
            }
        }
        if("OgreBrute_Ritual"==RitualCard)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="OgreBrute")
                    {
                        OgreBrute_CultistTokenCount++;
                        gc.drawImage(new Image("File:CultistToken.png",tileLengths,tileLengths,false,false),10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths), tileLengths / 2, tileLengths / 2);
                    }
                }
            }
        }
        if("ParadoxPuzzle_Ritual"==RitualCard)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="ParadoxPuzzle")
                    {
                        ParadoxPuzzle_CultistTokenCount++;
                        gc.drawImage(new Image("File:CultistToken.png",tileLengths,tileLengths,false,false),10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths), tileLengths / 2, tileLengths / 2);
                    }
                }
            }
        }
        if("PendulumBlades_Ritual"==RitualCard)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="PendulumBlades")
                    {
                        PendulumBlades_CultistTokenCount++;
                        gc.drawImage(new Image("File:CultistToken.png",tileLengths,tileLengths,false,false),10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths), tileLengths / 2, tileLengths / 2);
                    }
                }
            }
        }
        if("Psychomancer_Ritual"==RitualCard)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="Psychomancer")
                    {
                        Psychomancer_CultistTokenCount++;
                        gc.drawImage(new Image("File:CultistToken.png",tileLengths,tileLengths,false,false),10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths), tileLengths / 2, tileLengths / 2);
                    }
                }
            }
        }
        if("RedEvent_Ritual"==RitualCard)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="VoraxsHeart")
                    {
                        RedEvent_CultistTokenCount++;
                        gc.drawImage(new Image("File:CultistToken.png",tileLengths,tileLengths,false,false),10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths), tileLengths / 2, tileLengths / 2);
                    }
                }
            }
        }
        if("SkeletalGuards_Ritual"==RitualCard)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="SkeletalGuards")
                    {
                        SkeletalGuards_CultistTokenCount++;
                        gc.drawImage(new Image("File:CultistToken.png",tileLengths,tileLengths,false,false),10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths), tileLengths / 2, tileLengths / 2);
                    }
                }
            }
        }
        if("SphynxsRiddle_Ritual"==RitualCard)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="SphynxsRiddle")
                    {
                        SphynxsRiddle_CultistTokenCount++;
                        gc.drawImage(new Image("File:CultistToken.png",tileLengths,tileLengths,false,false),10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths), tileLengths / 2, tileLengths / 2);
                    }
                }
            }
        }
        if("SpikedPit_Ritual"==RitualCard)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="SpikedPit")
                    {
                        SpikedPit_CultistTokenCount++;
                        gc.drawImage(new Image("File:CultistToken.png",tileLengths,tileLengths,false,false),10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths), tileLengths / 2, tileLengths / 2);
                    }
                }
            }
        }
        if("VoraciousPlant_Ritual"==RitualCard)
        {
            for (int r = 0; r < refGrid.length; r++) {
                for (int c = 0; c < refGrid[0].length; c++) {
                    if(refGrid[r][c]=="VoraciousPlant")
                    {
                        VoraciousPlant_CultistTokenCount++;
                        gc.drawImage(new Image("File:CultistToken.png",tileLengths,tileLengths,false,false),10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths), tileLengths / 2, tileLengths / 2);
                    }
                }
            }
        }
        CheckFor2CultistTokensAndRemoveTile();
    }
    public Image RitualStringToImage(String card)
    {
        if(card=="AcidJets_Ritual")
        {
            return AcidJets_Ritual_img;
        }
        if(card=="ArrowTrap_Ritual")
        {
            return ArrowTrap_Ritual_img;
        }
        if(card=="BlueEvent_Ritual")
        {
            return BlueEvent_Ritual_img;
        }
        if(card=="DarkSlime_Ritual")
        {
            return DarkSlime_Ritual_img;
        }
        if(card=="DenOfSnakes_Ritual")
        {
            return DenOfSnakes_Ritual_img;
        }
        if(card=="Dragonling_Ritual")
        {
            return Dragonling_Ritual_img;
        }
        if(card=="FelKnight_Ritual")
        {
            return FelKnight_Ritual_img;
        }
        if(card=="FloatingStones_Ritual")
        {
            return FloatingStones_Ritual_img;
        }
        if(card=="GreenEvent_Ritual")
        {
            return GreenEvent_Ritual_img;
        }
        if(card=="HallOfIllusion_Ritual")
        {
            return HallOfIllusion_Ritual_img;
        }
        if(card=="LaughingShadow_Ritual")
        {
            return LaughingShadow_Ritual_img;
        }
        if(card=="LavaLake_Ritual")
        {
            return LavaLake_Ritual_img;
        }
        if(card=="MimicChest_Ritual")
        {
            return MimicChest_Ritual_img;
        }
        if(card=="MindEater_Ritual")
        {
            return MindEater_Ritual_img;
        }
        if(card=="Minotaur_Ritual")
        {
            return Minotaur_Ritual_img;
        }
        if(card=="OgreBrute_Ritual")
        {
            return OgreBrute_Ritual_img;
        }
        if(card=="ParadoxPuzzle_Ritual")
        {
            return ParadoxPuzzle_Ritual_img;
        }
        if(card=="PendulumBlades_Ritual")
        {
            return PendulumBlades_Ritual_img;
        }
        if(card=="Psychomancer_Ritual")
        {
            return Psychomancer_Ritual_img;
        }
        if(card=="RedEvent_Ritual")
        {
            return RedEvent_Ritual_img;
        }
        if(card=="SkeletalGuards_Ritual")
        {
            return SkeletalGuards_Ritual_img;
        }
        if(card=="SphynxsRiddle_Ritual")
        {
            return SphynxsRiddle_Ritual_img;
        }
        if(card=="SpikedPit_Ritual")
        {
            return SpikedPit_Ritual_img;
        }
        if(card=="VoraciousPlant_Ritual")
        {
            return VoraciousPlant_Ritual_img;
        }
        return null;
    }
    public void pickTopCardFromRitualDeckAndDrawToTheSideAndOnBoard()
    {
        for (int i = 0; i < NormalThreatLevel_NumOfCards.get(ThreatIndex); i++) {
            String TopCard = StackOfRitualCards.remove(0);
            AlreadyOpenedRitualCardStack.add(TopCard);
            gc.fillRect(10 + Outline.getWidth() + 20, 10 + 20 + tileLengths + 20, tileLengths, tileLengths * 2);
            gc.drawImage(RitualStringToImage(TopCard), 10 + Outline.getWidth() + 20 + tileLengths, 10 + 20 + tileLengths + 20, tileLengths, tileLengths * 2);
            addCultistTokenToChamberAndDrawToken(TopCard);
            CheckFor2CultistTokensAndRemoveTile();
            drawRefBoardAgain();
        }
        Phase=1;
     //   setVisibilityBasedOnPhase();
        drawPlayPage(gc);
        drawActionPoints();
        drawActionPoints();
        drawRefBoardAgain();
        drawToken();
        drawCultist();
        drawHeros();
        drawThreatLevel();
        drawInventoryForPlayers();
    }
    public void drawThreatLevel()
    {
        gc.setStroke(Color.RED);
        gc.strokeText("Threat Level: "+(9-ThreatIndex),10+Outline.getWidth()+20+.01+(tileLengths*2/3)+55+80,10+20+.01+480+100+5+(15*ThreatIndex));
    }
    public boolean checkIfClickOnRitualStack(double x, double y)
    {
        if(10+Outline.getWidth()+20+.01<=x && x<=10+Outline.getWidth()+20+.01+tileLengths-.01)
        {
            if(10+20+.01+tileLengths+20<=y && y<=10+20+.01+tileLengths+20 + tileLengths*2-.01)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
    public void drawRitualStack()
    {
        gc.setFill(Color.BLACK);
        gc.setStroke(Color.INDIANRED);
        gc.fillRect(10+Outline.getWidth()+20+.01,10+20+.01+tileLengths+20, tileLengths-.01, tileLengths-.01);
        gc.strokeRect(10+Outline.getWidth()+20,10+20+tileLengths+20,tileLengths,tileLengths*2);
    }
    public ArrayList<Integer> getPlayersOnSameTileAsCurrentPlayer()
    {
        ArrayList<Integer> Array = new ArrayList<>();
        if(CurrentPlayer_Turn==1)
        {
            if((Player2_Position[0]!=-1) && refGrid[Player1_Position[0]][Player1_Position[1]]==refGrid[Player2_Position[0]][Player2_Position[1]])
            {
                Array.add(2);
            }
            if((Player3_Position[0]!=-1) && refGrid[Player1_Position[0]][Player1_Position[1]]==refGrid[Player3_Position[0]][Player3_Position[1]])
            {
                Array.add(3);
            }
            if((Player4_Position[0]!=-1) && refGrid[Player1_Position[0]][Player1_Position[1]]==refGrid[Player4_Position[0]][Player4_Position[1]])
            {
                Array.add(4);
            }
            if((Player5_Position[0]!=-1) && refGrid[Player1_Position[0]][Player1_Position[1]]==refGrid[Player5_Position[0]][Player5_Position[1]])
            {
                Array.add(5);
            }
            if((Player6_Position[0]!=-1) && refGrid[Player1_Position[0]][Player1_Position[1]]==refGrid[Player6_Position[0]][Player6_Position[1]])
            {
                Array.add(6);
            }
        }
        if(CurrentPlayer_Turn==2)
        {
            if(refGrid[Player2_Position[0]][Player2_Position[1]]==refGrid[Player1_Position[0]][Player1_Position[1]])
            {
                Array.add(1);
            }
            if((Player3_Position[0]!=-1) && refGrid[Player2_Position[0]][Player2_Position[1]]==refGrid[Player3_Position[0]][Player3_Position[1]])
            {
                Array.add(3);
            }
            if((Player4_Position[0]!=-1) && refGrid[Player2_Position[0]][Player2_Position[1]]==refGrid[Player4_Position[0]][Player4_Position[1]])
            {
                Array.add(4);
            }
            if((Player5_Position[0]!=-1) && refGrid[Player2_Position[0]][Player2_Position[1]]==refGrid[Player5_Position[0]][Player5_Position[1]])
            {
                Array.add(5);
            }
            if((Player6_Position[0]!=-1) && refGrid[Player2_Position[0]][Player2_Position[1]]==refGrid[Player6_Position[0]][Player6_Position[1]])
            {
                Array.add(6);
            }
        }
        if(CurrentPlayer_Turn==3)
        {
            if(refGrid[Player3_Position[0]][Player3_Position[1]]==refGrid[Player1_Position[0]][Player1_Position[1]])
            {
                Array.add(1);
            }
            if(refGrid[Player3_Position[0]][Player3_Position[1]]==refGrid[Player2_Position[0]][Player2_Position[1]])
            {
                Array.add(2);
            }
            if((Player4_Position[0]!=-1) && refGrid[Player3_Position[0]][Player3_Position[1]]==refGrid[Player4_Position[0]][Player4_Position[1]])
            {
                Array.add(4);
            }
            if((Player5_Position[0]!=-1) && refGrid[Player3_Position[0]][Player3_Position[1]]==refGrid[Player5_Position[0]][Player5_Position[1]])
            {
                Array.add(5);
            }
            if((Player6_Position[0]!=-1) && refGrid[Player3_Position[0]][Player3_Position[1]]==refGrid[Player6_Position[0]][Player6_Position[1]])
            {
                Array.add(6);
            }
        }
        if(CurrentPlayer_Turn==4)
        {
            if(refGrid[Player4_Position[0]][Player4_Position[1]]==refGrid[Player1_Position[0]][Player1_Position[1]])
            {
                Array.add(1);
            }
            if(refGrid[Player4_Position[0]][Player4_Position[1]]==refGrid[Player2_Position[0]][Player2_Position[1]])
            {
                Array.add(2);
            }
            if(refGrid[Player4_Position[0]][Player4_Position[1]]==refGrid[Player3_Position[0]][Player3_Position[1]])
            {
                Array.add(3);
            }
            if((Player5_Position[0]!=-1) && refGrid[Player4_Position[0]][Player4_Position[1]]==refGrid[Player5_Position[0]][Player5_Position[1]])
            {
                Array.add(5);
            }
            if((Player6_Position[0]!=-1) && refGrid[Player4_Position[0]][Player4_Position[1]]==refGrid[Player6_Position[0]][Player6_Position[1]])
            {
                Array.add(6);
            }
        }
        if(CurrentPlayer_Turn==5)
        {
            if(refGrid[Player5_Position[0]][Player5_Position[1]]==refGrid[Player1_Position[0]][Player1_Position[1]])
            {
                Array.add(1);
            }
            if(refGrid[Player5_Position[0]][Player5_Position[1]]==refGrid[Player2_Position[0]][Player2_Position[1]])
            {
                Array.add(2);
            }
            if(refGrid[Player5_Position[0]][Player5_Position[1]]==refGrid[Player3_Position[0]][Player3_Position[1]])
            {
                Array.add(3);
            }
            if(refGrid[Player5_Position[0]][Player5_Position[1]]==refGrid[Player4_Position[0]][Player4_Position[1]])
            {
                Array.add(4);
            }
            if((Player6_Position[0]!=-1) && refGrid[Player5_Position[0]][Player5_Position[1]]==refGrid[Player6_Position[0]][Player6_Position[1]])
            {
                Array.add(6);
            }
        }
        if(CurrentPlayer_Turn==6)
        {
            if(refGrid[Player6_Position[0]][Player6_Position[1]]==refGrid[Player1_Position[0]][Player1_Position[1]])
            {
                Array.add(1);
            }
            if(refGrid[Player6_Position[0]][Player6_Position[1]]==refGrid[Player2_Position[0]][Player2_Position[1]])
            {
                Array.add(2);
            }
            if(refGrid[Player6_Position[0]][Player6_Position[1]]==refGrid[Player3_Position[0]][Player3_Position[1]])
            {
                Array.add(3);
            }
            if(refGrid[Player6_Position[0]][Player6_Position[1]]==refGrid[Player4_Position[0]][Player4_Position[1]])
            {
                Array.add(4);
            }
            if(refGrid[Player6_Position[0]][Player6_Position[1]]==refGrid[Player5_Position[0]][Player5_Position[1]])
            {
                Array.add(5);
            }
        }
        return Array;
    }
    public void addToPlayerInventory(int player, String Item)
    {
        if(player==1)
        {
            Player1Inventory.add(Item);
        }
        if(player==2)
        {
            Player2Inventory.add(Item);
        }
        if(player==3)
        {
            Player3Inventory.add(Item);
        }
        if(player==4)
        {
            Player4Inventory.add(Item);
        }
        if(player==5)
        {
            Player5Inventory.add(Item);
        }
        if(player==6)
        {
            Player6Inventory.add(Item);
        }

    }
    public void tradeTo(int playerFrom, int playerTo, int indexItem)
    {
            if (playerFrom == 1) {
                String getItem = Player1Inventory.get(indexItem);
                Player1Inventory.remove(indexItem);
                addToPlayerInventory(playerTo, getItem);
            }
            if (playerFrom == 2) {
                String getItem = Player2Inventory.get(indexItem);
                Player2Inventory.remove(indexItem);
                addToPlayerInventory(playerTo, getItem);
            }
            if (playerFrom == 3) {
                String getItem = Player3Inventory.get(indexItem);
                Player3Inventory.remove(indexItem);
                addToPlayerInventory(playerTo, getItem);
            }
            if (playerFrom == 4) {
                String getItem = Player4Inventory.get(indexItem);
                Player4Inventory.remove(indexItem);
                addToPlayerInventory(playerTo, getItem);
            }
            if (playerFrom == 5) {
                String getItem = Player5Inventory.get(indexItem);
                Player5Inventory.remove(indexItem);
                addToPlayerInventory(playerTo, getItem);
            }
            if (playerFrom == 6) {
                String getItem = Player6Inventory.get(indexItem);
                Player6Inventory.remove(indexItem);
                addToPlayerInventory(playerTo, getItem);
        }
    }
    public void clearCB()
    {
        for (int i = 0; i <Player1_TakeFrom.getItems().size() ; i++) {
            Player1_TakeFrom.getItems().remove(i);
            i--;
        }
        for (int i = 0; i <Player2_TakeFrom.getItems().size() ; i++) {
            Player2_TakeFrom.getItems().remove(i);
            i--;
        }
        for (int i = 0; i <Player3_TakeFrom.getItems().size() ; i++) {
            Player3_TakeFrom.getItems().remove(i);
            i--;
        }
        for (int i = 0; i <Player4_TakeFrom.getItems().size() ; i++) {
            Player4_TakeFrom.getItems().remove(i);
            i--;
        }
        for (int i = 0; i <Player5_TakeFrom.getItems().size() ; i++) {
            Player5_TakeFrom.getItems().remove(i);
            i--;
        }
        for (int i = 0; i <Player6_TakeFrom.getItems().size() ; i++) {
            Player6_TakeFrom.getItems().remove(i);
            i--;
        }

        for (int i = 0; i <Player1_Giveto.getItems().size() ; i++) {
            Player1_Giveto.getItems().remove(i);
            i--;
        }
        for (int i = 0; i <Player2_Giveto.getItems().size() ; i++) {
            Player2_Giveto.getItems().remove(i);
            i--;
        }
        for (int i = 0; i <Player3_Giveto.getItems().size() ; i++) {
            Player3_Giveto.getItems().remove(i);
            i--;
        }
        for (int i = 0; i <Player4_Giveto.getItems().size() ; i++) {
            Player4_Giveto.getItems().remove(i);
            i--;
        }
        for (int i = 0; i <Player5_Giveto.getItems().size() ; i++) {
            Player5_Giveto.getItems().remove(i);
            i--;
        }
        for (int i = 0; i <Player6_Giveto.getItems().size() ; i++) {
            Player6_Giveto.getItems().remove(i);
            i--;
        }

    }
    public void addAllInventoryItemsToGiveToCB()
    {
        if(CurrentPlayer_Turn==1) {
            for (int i = 0; i < Player1Inventory.size(); i++) {
                if(Player2_Giveto.getItems().contains(i)!=true) {
                    Player2_Giveto.getItems().add(i);
                }
            }
            for (int i = 0; i < Player1Inventory.size(); i++) {
                if(Player3_Giveto.getItems().contains(i)!=true) {
                    Player3_Giveto.getItems().add(i);
                }
            }
            for (int i = 0; i < Player1Inventory.size(); i++) {
                if(Player4_Giveto.getItems().contains(i)!=true) {
                    Player4_Giveto.getItems().add(i);
                }
            }
            for (int i = 0; i < Player1Inventory.size(); i++) {
                if(Player5_Giveto.getItems().contains(i)!=true) {
                    Player5_Giveto.getItems().add(i);
                }
            }
            for (int i = 0; i < Player1Inventory.size(); i++) {
                if(Player6_Giveto.getItems().contains(i)!=true) {
                    Player6_Giveto.getItems().add(i);
                }
            }
        }
        if(CurrentPlayer_Turn==2) {
            for (int i = 0; i < Player2Inventory.size(); i++) {
                if(Player1_Giveto.getItems().contains(i)!=true) {
                    Player1_Giveto.getItems().add(i);
                }
            }
            for (int i = 0; i < Player2Inventory.size(); i++) {
                if(Player3_Giveto.getItems().contains(i)!=true) {
                    Player3_Giveto.getItems().add(i);
                }
            }
            for (int i = 0; i < Player2Inventory.size(); i++) {
                if(Player4_Giveto.getItems().contains(i)!=true) {
                    Player4_Giveto.getItems().add(i);
                }
            }
            for (int i = 0; i < Player2Inventory.size(); i++) {
                if(Player5_Giveto.getItems().contains(i)!=true) {
                    Player5_Giveto.getItems().add(i);
                }
            }
            for (int i = 0; i < Player2Inventory.size(); i++) {
                if(Player6_Giveto.getItems().contains(i)!=true) {
                    Player6_Giveto.getItems().add(i);
                }
            }
        }
        if(CurrentPlayer_Turn==3) {
            for (int i = 0; i < Player3Inventory.size(); i++) {
                if(Player1_Giveto.getItems().contains(i)!=true) {
                    Player1_Giveto.getItems().add(i);
                }
            }
            for (int i = 0; i < Player3Inventory.size(); i++) {
                if(Player2_Giveto.getItems().contains(i)!=true) {
                    Player2_Giveto.getItems().add(i);
                }
            }
            for (int i = 0; i < Player3Inventory.size(); i++) {
                if(Player4_Giveto.getItems().contains(i)!=true) {
                    Player4_Giveto.getItems().add(i);
                }
            }
            for (int i = 0; i < Player3Inventory.size(); i++) {
                if(Player5_Giveto.getItems().contains(i)!=true) {
                    Player5_Giveto.getItems().add(i);
                }
            }
            for (int i = 0; i < Player3Inventory.size(); i++) {
                if(Player6_Giveto.getItems().contains(i)!=true) {
                    Player6_Giveto.getItems().add(i);
                }
            }
        }
        if(CurrentPlayer_Turn==4) {
            for (int i = 0; i < Player4Inventory.size(); i++) {
                if(Player1_Giveto.getItems().contains(i)!=true) {
                    Player1_Giveto.getItems().add(i);
                }
            }
            for (int i = 0; i < Player4Inventory.size(); i++) {
                if(Player2_Giveto.getItems().contains(i)!=true) {
                    Player2_Giveto.getItems().add(i);
                }
            }
            for (int i = 0; i < Player4Inventory.size(); i++) {
                if(Player3_Giveto.getItems().contains(i)!=true) {
                    Player3_Giveto.getItems().add(i);
                }
            }
            for (int i = 0; i < Player4Inventory.size(); i++) {
                if(Player5_Giveto.getItems().contains(i)!=true) {
                    Player5_Giveto.getItems().add(i);
                }
            }
            for (int i = 0; i < Player4Inventory.size(); i++) {
                if(Player6_Giveto.getItems().contains(i)!=true) {
                    Player6_Giveto.getItems().add(i);
                }
            }
        }
        if(CurrentPlayer_Turn==5) {
            for (int i = 0; i < Player5Inventory.size(); i++) {
                if(Player1_Giveto.getItems().contains(i)!=true) {
                    Player1_Giveto.getItems().add(i);
                }
            }
            for (int i = 0; i < Player5Inventory.size(); i++) {
                if(Player2_Giveto.getItems().contains(i)!=true) {
                    Player2_Giveto.getItems().add(i);
                }
            }
            for (int i = 0; i < Player5Inventory.size(); i++) {
                if(Player3_Giveto.getItems().contains(i)!=true) {
                    Player3_Giveto.getItems().add(i);
                }
            }
            for (int i = 0; i < Player5Inventory.size(); i++) {
                if(Player4_Giveto.getItems().contains(i)!=true) {
                    Player4_Giveto.getItems().add(i);
                }
            }
            for (int i = 0; i < Player5Inventory.size(); i++) {
                if(Player6_Giveto.getItems().contains(i)!=true) {
                    Player6_Giveto.getItems().add(i);
                }
            }
        }
        if(CurrentPlayer_Turn==6) {
            for (int i = 0; i < Player6Inventory.size(); i++) {
                if(Player1_Giveto.getItems().contains(i)!=true) {
                    Player1_Giveto.getItems().add(i);
                }
            }
            for (int i = 0; i < Player6Inventory.size(); i++) {
                if(Player2_Giveto.getItems().contains(i)!=true) {
                    Player2_Giveto.getItems().add(i);
                }
            }
            for (int i = 0; i < Player6Inventory.size(); i++) {
                if(Player3_Giveto.getItems().contains(i)!=true) {
                    Player3_Giveto.getItems().add(i);
                }
            }
            for (int i = 0; i < Player6Inventory.size(); i++) {
                if(Player4_Giveto.getItems().contains(i)!=true) {
                    Player4_Giveto.getItems().add(i);
                }
            }
            for (int i = 0; i < Player6Inventory.size(); i++) {
                if(Player5_Giveto.getItems().contains(i)!=true) {
                    Player5_Giveto.getItems().add(i);
                }
            }
        }

    }
    public void addAllInventoryItemsToTakeFromCB()
    {
        if(CurrentPlayer_Turn==1) {
            for (int i = 0; i < Player2Inventory.size(); i++) {
                if(Player2_TakeFrom.getItems().contains(i)==false) {
                    Player2_TakeFrom.getItems().add(i);
                }
            }
            for (int i = 0; i < Player3Inventory.size(); i++) {
                if(Player3_TakeFrom.getItems().contains(i)==false) {
                    Player3_TakeFrom.getItems().add(i);
                }
            }
            for (int i = 0; i < Player4Inventory.size(); i++) {
                if(Player4_TakeFrom.getItems().contains(i)==false) {
                    Player4_TakeFrom.getItems().add(i);
                }
            }
            for (int i = 0; i < Player5Inventory.size(); i++) {
                if(Player5_TakeFrom.getItems().contains(i)==false) {
                    Player5_TakeFrom.getItems().add(i);
                }
            }
            for (int i = 0; i < Player6Inventory.size(); i++) {
                if(Player6_TakeFrom.getItems().contains(i)==false) {
                    Player6_TakeFrom.getItems().add(i);
                }
            }
        }
        if(CurrentPlayer_Turn==2) {
            for (int i = 0; i < Player1Inventory.size(); i++) {
                if(Player1_TakeFrom.getItems().contains(i)==false) {
                    Player1_TakeFrom.getItems().add(i);
                }
            }
            for (int i = 0; i < Player3Inventory.size(); i++) {
                if(Player3_TakeFrom.getItems().contains(i)==false) {
                    Player3_TakeFrom.getItems().add(i);
                }
            }
            for (int i = 0; i < Player4Inventory.size(); i++) {
                if(Player4_TakeFrom.getItems().contains(i)==false) {
                    Player4_TakeFrom.getItems().add(i);
                }
            }
            for (int i = 0; i < Player5Inventory.size(); i++) {
                if(Player5_TakeFrom.getItems().contains(i)==false) {
                    Player5_TakeFrom.getItems().add(i);
                }
            }
            for (int i = 0; i < Player6Inventory.size(); i++) {
                if(Player6_TakeFrom.getItems().contains(i)==false) {
                    Player6_TakeFrom.getItems().add(i);
                }
            }
        }
        if(CurrentPlayer_Turn==3) {
            for (int i = 0; i < Player1Inventory.size(); i++) {
                if(Player1_TakeFrom.getItems().contains(i)==false) {
                    Player1_TakeFrom.getItems().add(i);
                }
            }
            for (int i = 0; i < Player2Inventory.size(); i++) {
                if(Player2_TakeFrom.getItems().contains(i)==false) {
                    Player2_TakeFrom.getItems().add(i);
                }
            }
            for (int i = 0; i < Player4Inventory.size(); i++) {
                if(Player4_TakeFrom.getItems().contains(i)==false) {
                    Player4_TakeFrom.getItems().add(i);
                }
            }
            for (int i = 0; i < Player5Inventory.size(); i++) {
                if(Player5_TakeFrom.getItems().contains(i)==false) {
                    Player5_TakeFrom.getItems().add(i);
                }
            }
            for (int i = 0; i < Player6Inventory.size(); i++) {
                if(Player6_TakeFrom.getItems().contains(i)==false) {
                    Player6_TakeFrom.getItems().add(i);
                }
            }
        }
        if(CurrentPlayer_Turn==4) {
            for (int i = 0; i < Player1Inventory.size(); i++) {
                if(Player1_TakeFrom.getItems().contains(i)==false) {
                    Player1_TakeFrom.getItems().add(i);
                }
            }
            for (int i = 0; i < Player2Inventory.size(); i++) {
                if(Player2_TakeFrom.getItems().contains(i)==false) {
                    Player2_TakeFrom.getItems().add(i);
                }
            }
            for (int i = 0; i < Player3Inventory.size(); i++) {
                if(Player3_TakeFrom.getItems().contains(i)==false) {
                    Player3_TakeFrom.getItems().add(i);
                }
            }
            for (int i = 0; i < Player5Inventory.size(); i++) {
                if(Player5_TakeFrom.getItems().contains(i)==false) {
                    Player5_TakeFrom.getItems().add(i);
                }
            }
            for (int i = 0; i < Player6Inventory.size(); i++) {
                if(Player6_TakeFrom.getItems().contains(i)==false) {
                    Player6_TakeFrom.getItems().add(i);
                }
            }
        }
        if(CurrentPlayer_Turn==5) {
            for (int i = 0; i < Player1Inventory.size(); i++) {
                if(Player1_TakeFrom.getItems().contains(i)==false) {
                    Player1_TakeFrom.getItems().add(i);
                }
            }
            for (int i = 0; i < Player2Inventory.size(); i++) {
                if(Player2_TakeFrom.getItems().contains(i)==false) {
                    Player2_TakeFrom.getItems().add(i);
                }
            }
            for (int i = 0; i < Player3Inventory.size(); i++) {
                if(Player3_TakeFrom.getItems().contains(i)==false) {
                    Player3_TakeFrom.getItems().add(i);
                }
            }
            for (int i = 0; i < Player4Inventory.size(); i++) {
                if(Player4_TakeFrom.getItems().contains(i)==false) {
                    Player4_TakeFrom.getItems().add(i);
                }
            }
            for (int i = 0; i < Player6Inventory.size(); i++) {
                if(Player6_TakeFrom.getItems().contains(i)==false) {
                    Player6_TakeFrom.getItems().add(i);
                }
            }
        }
        if(CurrentPlayer_Turn==6) {
            for (int i = 0; i < Player1Inventory.size(); i++) {
                if(Player1_TakeFrom.getItems().contains(i)==false) {
                    Player1_TakeFrom.getItems().add(i);
                }
            }
            for (int i = 0; i < Player2Inventory.size(); i++) {
                if(Player2_TakeFrom.getItems().contains(i)==false) {
                    Player2_TakeFrom.getItems().add(i);
                }
            }
            for (int i = 0; i < Player3Inventory.size(); i++) {
                if(Player3_TakeFrom.getItems().contains(i)==false) {
                    Player3_TakeFrom.getItems().add(i);
                }
            }
            for (int i = 0; i < Player4Inventory.size(); i++) {
                if(Player4_TakeFrom.getItems().contains(i)==false) {
                    Player4_TakeFrom.getItems().add(i);
                }
            }
            for (int i = 0; i < Player5Inventory.size(); i++) {
                if(Player5_TakeFrom.getItems().contains(i)==false) {
                    Player5_TakeFrom.getItems().add(i);
                }
            }
        }

    }
    public void PlayerOnSameTileSetVisible()
    {
        ArrayList<Integer> Array = new ArrayList<>();
        if(CurrentPlayer_Turn==1)
        {
            if((Player2_Position[0]!=-1) && refGrid[Player1_Position[0]][Player1_Position[1]]==refGrid[Player2_Position[0]][Player2_Position[1]])
            {
                Array.add(1);
                Array.add(2);
            }
            if((Player3_Position[0]!=-1) && refGrid[Player1_Position[0]][Player1_Position[1]]==refGrid[Player3_Position[0]][Player3_Position[1]])
            {
                Array.add(1);
                Array.add(3);
            }
            if((Player4_Position[0]!=-1) && refGrid[Player1_Position[0]][Player1_Position[1]]==refGrid[Player4_Position[0]][Player4_Position[1]])
            {
                Array.add(1);
                Array.add(4);
            }
            if((Player5_Position[0]!=-1) && refGrid[Player1_Position[0]][Player1_Position[1]]==refGrid[Player5_Position[0]][Player5_Position[1]])
            {
                Array.add(1);
                Array.add(5);
            }
            if((Player6_Position[0]!=-1) && refGrid[Player1_Position[0]][Player1_Position[1]]==refGrid[Player6_Position[0]][Player6_Position[1]])
            {
                Array.add(1);
                Array.add(6);
            }
        }
        if(CurrentPlayer_Turn==2)
        {
            if(refGrid[Player2_Position[0]][Player2_Position[1]]==refGrid[Player1_Position[0]][Player1_Position[1]])
            {
                Array.add(2);
                Array.add(1);
            }
            if((Player3_Position[0]!=-1) && refGrid[Player2_Position[0]][Player2_Position[1]]==refGrid[Player3_Position[0]][Player3_Position[1]])
            {
                Array.add(2);
                Array.add(3);
            }
            if((Player4_Position[0]!=-1) && refGrid[Player2_Position[0]][Player2_Position[1]]==refGrid[Player4_Position[0]][Player4_Position[1]])
            {
                Array.add(2);
                Array.add(4);
            }
            if((Player5_Position[0]!=-1) && refGrid[Player2_Position[0]][Player2_Position[1]]==refGrid[Player5_Position[0]][Player5_Position[1]])
            {
                Array.add(2);
                Array.add(5);
            }
            if((Player6_Position[0]!=-1) && refGrid[Player2_Position[0]][Player2_Position[1]]==refGrid[Player6_Position[0]][Player6_Position[1]])
            {
                Array.add(2);
                Array.add(6);
            }
        }
        if(CurrentPlayer_Turn==3)
        {
            if(refGrid[Player3_Position[0]][Player3_Position[1]]==refGrid[Player1_Position[0]][Player1_Position[1]])
            {
                Array.add(3);
                Array.add(1);
            }
            if(refGrid[Player3_Position[0]][Player3_Position[1]]==refGrid[Player2_Position[0]][Player2_Position[1]])
            {
                Array.add(3);
                Array.add(2);
            }
            if((Player4_Position[0]!=-1) && refGrid[Player3_Position[0]][Player3_Position[1]]==refGrid[Player4_Position[0]][Player4_Position[1]])
            {
                Array.add(3);
                Array.add(4);
            }
            if((Player5_Position[0]!=-1) && refGrid[Player3_Position[0]][Player3_Position[1]]==refGrid[Player5_Position[0]][Player5_Position[1]])
            {
                Array.add(3);
                Array.add(5);
            }
            if((Player6_Position[0]!=-1) && refGrid[Player3_Position[0]][Player3_Position[1]]==refGrid[Player6_Position[0]][Player6_Position[1]])
            {
                Array.add(3);
                Array.add(6);
            }
        }
        if(CurrentPlayer_Turn==4)
        {
            if(refGrid[Player4_Position[0]][Player4_Position[1]]==refGrid[Player1_Position[0]][Player1_Position[1]])
            {
                Array.add(4);
                Array.add(1);
            }
            if(refGrid[Player4_Position[0]][Player4_Position[1]]==refGrid[Player2_Position[0]][Player2_Position[1]])
            {
                Array.add(4);
                Array.add(2);
            }
            if(refGrid[Player4_Position[0]][Player4_Position[1]]==refGrid[Player3_Position[0]][Player3_Position[1]])
            {
                Array.add(4);
                Array.add(3);
            }
            if((Player5_Position[0]!=-1) && refGrid[Player4_Position[0]][Player4_Position[1]]==refGrid[Player5_Position[0]][Player5_Position[1]])
            {
                Array.add(4);
                Array.add(5);
            }
            if((Player6_Position[0]!=-1) && refGrid[Player4_Position[0]][Player4_Position[1]]==refGrid[Player6_Position[0]][Player6_Position[1]])
            {
                Array.add(4);
                Array.add(6);
            }
        }
        if(CurrentPlayer_Turn==5)
        {
            if(refGrid[Player5_Position[0]][Player5_Position[1]]==refGrid[Player1_Position[0]][Player1_Position[1]])
            {
                Array.add(5);
                Array.add(1);
            }
            if(refGrid[Player5_Position[0]][Player5_Position[1]]==refGrid[Player2_Position[0]][Player2_Position[1]])
            {
                Array.add(5);
                Array.add(2);
            }
            if(refGrid[Player5_Position[0]][Player5_Position[1]]==refGrid[Player3_Position[0]][Player3_Position[1]])
            {
                Array.add(5);
                Array.add(3);
            }
            if(refGrid[Player5_Position[0]][Player5_Position[1]]==refGrid[Player4_Position[0]][Player4_Position[1]])
            {
                Array.add(5);
                Array.add(4);
            }
            if((Player6_Position[0]!=-1) && refGrid[Player5_Position[0]][Player5_Position[1]]==refGrid[Player6_Position[0]][Player6_Position[1]])
            {
                Array.add(5);
                Array.add(6);
            }
        }
        if(CurrentPlayer_Turn==6)
        {
            if(refGrid[Player6_Position[0]][Player6_Position[1]]==refGrid[Player1_Position[0]][Player1_Position[1]])
            {
                Array.add(6);
                Array.add(1);
            }
            if(refGrid[Player6_Position[0]][Player6_Position[1]]==refGrid[Player2_Position[0]][Player2_Position[1]])
            {
                Array.add(6);
                Array.add(2);
            }
            if(refGrid[Player6_Position[0]][Player6_Position[1]]==refGrid[Player3_Position[0]][Player3_Position[1]])
            {
                Array.add(6);
                Array.add(3);
            }
            if(refGrid[Player6_Position[0]][Player6_Position[1]]==refGrid[Player4_Position[0]][Player4_Position[1]])
            {
                Array.add(6);
                Array.add(4);
            }
            if(refGrid[Player6_Position[0]][Player6_Position[1]]==refGrid[Player5_Position[0]][Player5_Position[1]])
            {
                Array.add(6);
                Array.add(5);
            }
        }
            if(Array.contains(1)){Player1_Giveto.setVisible(true); Player1_TakeFrom.setVisible(true);} else {Player1_Giveto.setVisible(false); Player1_TakeFrom.setVisible(false);}
            if(Array.contains(2)){Player2_Giveto.setVisible(true); Player2_TakeFrom.setVisible(true);} else {Player2_Giveto.setVisible(false); Player2_TakeFrom.setVisible(false);}
            if(Array.contains(3)){Player3_Giveto.setVisible(true); Player3_TakeFrom.setVisible(true);} else {Player3_Giveto.setVisible(false); Player3_TakeFrom.setVisible(false);}
            if(Array.contains(4)){Player4_Giveto.setVisible(true); Player4_TakeFrom.setVisible(true);} else {Player4_Giveto.setVisible(false); Player4_TakeFrom.setVisible(false);}
            if(Array.contains(5)){Player5_Giveto.setVisible(true); Player5_TakeFrom.setVisible(true);} else {Player5_Giveto.setVisible(false); Player5_TakeFrom.setVisible(false);}
            if(Array.contains(6)){Player6_Giveto.setVisible(true); Player6_TakeFrom.setVisible(true);} else {Player6_Giveto.setVisible(false); Player6_TakeFrom.setVisible(false);}

    }
    public void destroyRelic()
    {
        if(CurrentPlayer_Turn==1)
        {
            checkAbleToBreakDarkRelic(refGrid[Player1_Position[0]][Player1_Position[1]],1);
            remove6Tokens(getTileToken(Player1_Position[0],Player1_Position[1]));
        }
        if(CurrentPlayer_Turn==2)
        {
            checkAbleToBreakDarkRelic(refGrid[Player2_Position[0]][Player2_Position[1]],2);
            remove6Tokens(getTileToken(Player2_Position[0],Player2_Position[1]));
        }
        if(CurrentPlayer_Turn==3)
        {
            checkAbleToBreakDarkRelic(refGrid[Player3_Position[0]][Player3_Position[1]],3);
            remove6Tokens(getTileToken(Player3_Position[0],Player3_Position[1]));

        }
        if(CurrentPlayer_Turn==4)
        {
            checkAbleToBreakDarkRelic(refGrid[Player4_Position[0]][Player4_Position[1]],4);
            remove6Tokens(getTileToken(Player4_Position[0],Player4_Position[1]));
        }
        if(CurrentPlayer_Turn==5)
        {
            checkAbleToBreakDarkRelic(refGrid[Player5_Position[0]][Player5_Position[1]],5);
            remove6Tokens(getTileToken(Player5_Position[0],Player5_Position[1]));
        }
        if(CurrentPlayer_Turn==6)
        {
            checkAbleToBreakDarkRelic(refGrid[Player6_Position[0]][Player6_Position[1]],6);
            remove6Tokens(getTileToken(Player6_Position[0],Player6_Position[1]));

        }
    }
    public boolean[] checkForSixTokensForDestroyingDarkRelic(int player)
    {
        int countPick=0;
        int countKey=0;
        int countScroll=0;
        boolean[] result = new boolean[3];
        if (player==1) {
            for (int i = 0; i < Player1Inventory.size(); i++) {
                if (Player1Inventory.get(i) == "Pick") {
                    countPick++;
                }
                if (Player1Inventory.get(i) == "Key") {
                    countKey++;
                }
                if (Player1Inventory.get(i) == "Scroll") {
                    countScroll++;
                }
            }
        }
        if (player==2) {
            for (int i = 0; i < Player2Inventory.size(); i++) {
                //------
                if (Player2Inventory.get(i) == "Pick") {
                    countPick++;
                }
                if (Player2Inventory.get(i) == "Key") {
                    countKey++;
                }
                if (Player2Inventory.get(i) == "Scroll") {
                    countScroll++;
                }
            }
        }
        if (player==3) {
            for (int i = 0; i < Player3Inventory.size(); i++) {//----------
                if (Player3Inventory.get(i) == "Pick") {
                    countPick++;
                }
                if (Player3Inventory.get(i) == "Key") {
                    countKey++;
                }
                if (Player3Inventory.get(i) == "Scroll") {
                    countScroll++;
                }
            }
        }
            //---------
        if (player==4) {
            for (int i = 0; i < Player4Inventory.size(); i++) {
                if (Player4Inventory.get(i) == "Pick") {
                    countPick++;
                }
                if (Player4Inventory.get(i) == "Key") {
                    countKey++;
                }
                if (Player4Inventory.get(i) == "Scroll") {
                    countScroll++;
                }
            }
        }
        if (player==5) {
            for (int i = 0; i < Player5Inventory.size(); i++) {
                if (Player5Inventory.get(i) == "Pick") {
                    countPick++;
                }
                if (Player5Inventory.get(i) == "Key") {
                    countKey++;
                }
                if (Player5Inventory.get(i) == "Scroll") {
                    countScroll++;
                }
            }
        }
        if (player==6) {
            for (int i = 0; i < Player6Inventory.size(); i++) {
                //---------------
                if (Player6Inventory.get(i) == "Pick") {
                    countPick++;
                }
                if (Player6Inventory.get(i) == "Key") {
                    countKey++;
                }
                if (Player6Inventory.get(i) == "Scroll") {
                    countScroll++;
                }
            }
        }
        if(countPick>=6)
        {
            result[0]=true;
        }
        else
        {
            result[0]=false;
        }
        if(countKey>=6)
        {
            result[1]=true;
        }
        else
        {
            result[1]=false;
        }
        if(countScroll>=6)
        {
            result[2]=true;
        }
        else
        {
            result[2]=false;
        }

        return result;
    }
    public void remove6Tokens(String token)
    {
        int c = 0;
        if(CurrentPlayer_Turn==1){
            for (int i = 0; i < Player1Inventory.size(); i++) {
                if(token=="BrokenGem" && Player1Inventory.get(i)=="Key")
                {
                    Player1Inventory.remove(i);
                    i--;
                    c++;
                }
                if(token=="BrokenBook" && Player1Inventory.get(i)=="Scroll")
                {
                    Player1Inventory.remove(i);
                    i--;
                    c++;
                }
                if(token=="BrokenHeart" && Player1Inventory.get(i)=="Pick")
                {
                    Player1Inventory.remove(i);
                    i--;
                    c++;
                }
                if(c==6)
                {
                    break;
                }
            }
        }
        if(CurrentPlayer_Turn==2){
            for (int i = 0; i < Player2Inventory.size(); i++) {
                if(token=="BrokenGem" && Player2Inventory.get(i)=="Key")
                {
                    Player2Inventory.remove(i);
                    i--;
                    c++;
                }
                if(token=="BrokenBook" && Player2Inventory.get(i)=="Scroll")
                {
                    Player2Inventory.remove(i);
                    i--;
                    c++;
                }
                if(token=="BrokenHeart" && Player2Inventory.get(i)=="Pick")
                {
                    Player2Inventory.remove(i);
                    i--;
                    c++;
                }
                if(c==6)
                {
                    break;
                }
            }
        }
        if(CurrentPlayer_Turn==3){
            for (int i = 0; i < Player3Inventory.size(); i++) {
                if(token=="BrokenGem" && Player3Inventory.get(i)=="Key")
                {
                    Player3Inventory.remove(i);
                    i--;
                    c++;
                }
                if(token=="BrokenBook" && Player3Inventory.get(i)=="Scroll")
                {
                    Player3Inventory.remove(i);
                    i--;
                    c++;
                }
                if(token=="BrokenHeart" && Player3Inventory.get(i)=="Pick")
                {
                    Player3Inventory.remove(i);
                    i--;
                    c++;
                }
                if(c==6)
                {
                    break;
                }
            }
        }
        if(CurrentPlayer_Turn==4){
            for (int i = 0; i < Player4Inventory.size(); i++) {
                if(token=="BrokenGem" && Player4Inventory.get(i)=="Key")
                {
                    Player4Inventory.remove(i);
                    i--;
                    c++;
                }
                if(token=="BrokenBook" && Player4Inventory.get(i)=="Scroll")
                {
                    Player4Inventory.remove(i);
                    i--;
                    c++;
                }
                if(token=="BrokenHeart" && Player4Inventory.get(i)=="Pick")
                {
                    Player4Inventory.remove(i);
                    i--;
                    c++;
                }
                if(c==6)
                {
                    break;
                }
            }
        }
        if(CurrentPlayer_Turn==5){
            for (int i = 0; i < Player5Inventory.size(); i++) {
                if(token=="BrokenGem" && Player5Inventory.get(i)=="Key")
                {
                    Player5Inventory.remove(i);
                    i--;
                    c++;
                }
                if(token=="BrokenBook" && Player5Inventory.get(i)=="Scroll")
                {
                    Player5Inventory.remove(i);
                    i--;
                    c++;
                }
                if(token=="BrokenHeart" && Player5Inventory.get(i)=="Pick")
                {
                    Player5Inventory.remove(i);
                    i--;
                    c++;
                }
                if(c==6)
                {
                    break;
                }
            }
        }
        if(CurrentPlayer_Turn==6){
            for (int i = 0; i < Player6Inventory.size(); i++) {
                if(token=="BrokenGem" && Player6Inventory.get(i)=="Key")
                {
                    Player6Inventory.remove(i);
                    i--;
                    c++;
                }
                if(token=="BrokenBook" && Player6Inventory.get(i)=="Scroll")
                {
                    Player6Inventory.remove(i);
                    i--;
                    c++;
                }
                if(token=="BrokenHeart" && Player6Inventory.get(i)=="Pick")
                {
                    Player6Inventory.remove(i);
                    i--;
                    c++;
                }
                if(c==6)
                {
                    break;
                }
            }
        }
        drawToken();
        drawActionPoints();
        drawInventoryForPlayers();
    }
    public void checkAbleToBreakDarkRelic(String tile, int player)
    {
        if(tile=="VoraxsFocus" && checkForSixTokensForDestroyingDarkRelic(player)[1]==true)
        {
            VoraxsFocus_DarkRelic="BrokenGem";
            isGemBroken=true;
        }
        if(tile=="VoraxsHeart" && checkForSixTokensForDestroyingDarkRelic(player)[0]==true)
        {
            VoraxsHeart_DarkRelic="BrokenHeart";
            isHeartBroken=true;
        }
        if(tile=="VoraxsKnowledge" && checkForSixTokensForDestroyingDarkRelic(player)[2]==true)
        {
            VoraxsKnowledge_DarkRelic = "BrokenBook";
            isBookBroken=true;
        }
        drawToken();
    }
    public String getTileToken(int r, int c)
    {
        if(refGrid[r][c] == "FireOfEidolon")
        {
            return "Fireball";
        }
        if(refGrid[r][c] == "VoraxsFocus" && isGemBroken)
        {
            return "BrokenGem";
        }
        if(refGrid[r][c] == "VoraxsHeart" && isHeartBroken)
        {
            return "BrokenHeart";
        }
        if(refGrid[r][c] == "VoraxsKnowledge" && isBookBroken)
        {
            return "BrokenBook";
        }
        if (refGrid[r][c] == "AcidJets" || refGrid[r][c] == "ArrowTraps" || refGrid[r][c] == "DenOfSnakes" || refGrid[r][c] == "FloatingStones" || refGrid[r][c] == "LavaLake" || refGrid[r][c] == "PendulumBlades" || refGrid[r][c] == "SpikedPit") {
            return "Key";
        }
        if (refGrid[r][c] == "DarkSlime" || refGrid[r][c] == "Dragonling" || refGrid[r][c] == "FelKnight" || refGrid[r][c] == "Minotaur" || refGrid[r][c] == "OgreBrute" || refGrid[r][c] == "SkeletalGuards" || refGrid[r][c] == "VoraciousPlant") {
            return "Pick";
        }
        if (refGrid[r][c] == "HallOfIllusion" || refGrid[r][c] == "LaughingShadow" || refGrid[r][c] == "MimicChest" || refGrid[r][c] == "MindEater" || refGrid[r][c] == "ParadoxPuzzle" || refGrid[r][c] == "Psychomancer" || refGrid[r][c] == "SphynxsRiddle") {
            return "Scroll";
        }
        return "";
    }
    public void reducePointsBasedOnCurrentPlayer(String tile, String Class)
    {
            if(Class=="Cleric") {
                if (tile == "Pick") {
                    CurrentPlayer_TotalActionPoints -= Cleric_pickUp_levels[0];
                }
                if (tile == "Key") {
                    CurrentPlayer_TotalActionPoints -= Cleric_pickUp_levels[1];
                }
                if (tile == "Scroll") {
                    CurrentPlayer_TotalActionPoints -= Cleric_pickUp_levels[2];
                }
            }
            if(Class=="DarkKnight") {
                if (tile == "Pick") {
                    CurrentPlayer_TotalActionPoints -= DarkKnight_pickUp_levels[0];
                }
                if (tile == "Key") {
                    CurrentPlayer_TotalActionPoints -= DarkKnight_pickUp_levels[1];
                }
                if (tile == "Scroll") {
                    CurrentPlayer_TotalActionPoints -= DarkKnight_pickUp_levels[2];
                }
            }
            if(Class=="Engineer") {
                if (tile == "Pick") {
                    CurrentPlayer_TotalActionPoints -= Engineer_pickUp_levels[0];
                }
                if (tile == "Key") {
                    CurrentPlayer_TotalActionPoints -= Engineer_pickUp_levels[1];
                }
                if (tile == "Scroll") {
                    CurrentPlayer_TotalActionPoints -= Engineer_pickUp_levels[2];
                }
            }
            if(Class=="Geomancer") {
                if (tile == "Pick") {
                    CurrentPlayer_TotalActionPoints -= Geomancer_pickUp_levels[0];
                }
                if (tile == "Key") {
                    CurrentPlayer_TotalActionPoints -= Geomancer_pickUp_levels[1];
                }
                if (tile == "Scroll") {
                    CurrentPlayer_TotalActionPoints -= Geomancer_pickUp_levels[2];
                }
            }
            if(Class=="Paladin") {
                if (tile == "Pick") {
                    CurrentPlayer_TotalActionPoints -= Paladin_pickUp_levels[0];
                }
                if (tile == "Key") {
                    CurrentPlayer_TotalActionPoints -= Paladin_pickUp_levels[1];
                }
                if (tile == "Scroll") {
                    CurrentPlayer_TotalActionPoints -= Paladin_pickUp_levels[2];
                }
            }
            if(Class=="Ranger") {
                if (tile == "Pick") {
                    CurrentPlayer_TotalActionPoints -= Ranger_pickUp_levels[0];
                }
                if (tile == "Key") {
                    CurrentPlayer_TotalActionPoints -= Ranger_pickUp_levels[1];
                }
                if (tile == "Scroll") {
                    CurrentPlayer_TotalActionPoints -= Ranger_pickUp_levels[2];
                }
            }
            if(Class=="Rogue") {
                if (tile == "Pick") {
                    CurrentPlayer_TotalActionPoints -= Rogue_pickUp_levels[0];
                }
                if (tile == "Key") {
                    CurrentPlayer_TotalActionPoints -= Rogue_pickUp_levels[1];
                }
                if (tile == "Scroll") {
                    CurrentPlayer_TotalActionPoints -= Rogue_pickUp_levels[2];
                }
            }
            if(Class=="Sage") {
                if (tile == "Pick") {
                    CurrentPlayer_TotalActionPoints -= Sage_pickUp_levels[0];
                }
                if (tile == "Key") {
                    CurrentPlayer_TotalActionPoints -= Sage_pickUp_levels[1];
                }
                if (tile == "Scroll") {
                    CurrentPlayer_TotalActionPoints -= Sage_pickUp_levels[2];
                }
            }
            if(Class=="Soldier") {
                if (tile == "Pick") {
                    CurrentPlayer_TotalActionPoints -= Soldier_pickUp_levels[0];
                }
                if (tile == "Key") {
                    CurrentPlayer_TotalActionPoints -= Soldier_pickUp_levels[1];
                }
                if (tile == "Scroll") {
                    CurrentPlayer_TotalActionPoints -= Soldier_pickUp_levels[2];
                }
            }
            if(Class=="Swordsman") {
                if (tile == "Pick") {
                    CurrentPlayer_TotalActionPoints -= Swordsman_pickUp_levels[0];
                }
                if (tile == "Key") {
                    CurrentPlayer_TotalActionPoints -= Swordsman_pickUp_levels[1];
                }
                if (tile == "Scroll") {
                    CurrentPlayer_TotalActionPoints -= Swordsman_pickUp_levels[2];
                }
            }
            if(Class=="Warrior") {
                if (tile == "Pick") {
                    CurrentPlayer_TotalActionPoints -= Warrior_pickUp_levels[0];
                }
                if (tile == "Key") {
                    CurrentPlayer_TotalActionPoints -= Warrior_pickUp_levels[1];
                }
                if (tile == "Scroll") {
                    CurrentPlayer_TotalActionPoints -= Warrior_pickUp_levels[2];
                }
            }
            if(Class=="Wizard") {
                if (tile == "Pick") {
                    CurrentPlayer_TotalActionPoints -= Wizard_pickUp_levels[0];
                }
                if (tile == "Key") {
                    CurrentPlayer_TotalActionPoints -= Wizard_pickUp_levels[1];
                }
                if (tile == "Scroll") {
                    CurrentPlayer_TotalActionPoints -= Wizard_pickUp_levels[2];
                }
            }
    }
    public void removeTokenFromTile(String Tile)
    {
        if("AcidJets"==Tile) {
            AcidJets_PowerToken="";
        }
        if("ArrowTraps"==Tile) {
            ArrowTraps_PowerToken="";
        }
        if("DarkSlime"==Tile) {
            DarkSlime_PowerToken="";
        }
        if("DenOfSnakes"==Tile) {
            DenOfSnakes_PowerToken="";
        }
        if("Dragonling"==Tile ) {
            Dragonling_PowerToken="";
        }
        if("FelKnight"==Tile  ) {
            FelKnight_PowerToken="";
        }
        if("FloatingStones"==Tile)
        {
            FloatingStones_PowerToken="";
        }
        if("HallOfIllusion" ==Tile)
        {
            HallOfIllusion_PowerToken="";
        }
        if("LaughingShadow"==Tile)
        {
            LaughingShadow_PowerToken="";
        }
        if("LavaLake"==Tile) {
            LavaLake_PowerToken="";
        }
        if("MimicChest"==Tile) {
            MimicChest_PowerToken="";
        }
        if("MindEater"==Tile) {
            MindEater_PowerToken="";
        }
        if("Minotaur"==Tile) {
            Minotaur_PowerToken="";
        }
        if("OgreBrute"==Tile) {
            OgreBrute_PowerToken="";
        }
        if("ParadoxPuzzle" ==Tile) {
            ParadoxPuzzle_PowerToken="";
        }
        if("PendulumBlades"==Tile)
        {
            PendulumBlades_PowerToken="";
        }
        if("Psychomancer" ==Tile) {
            Psychomancer_PowerToken="";
        }
        if("SkeletalGuards"==Tile)
        {
            SkeletalGuards_PowerToken="";
        }
        if("SphynxsRiddle" ==Tile) {
            SphynxsRiddle_PowerToken="";
        }
        if("SpikedPit" ==Tile)
        {
            SpikedPit_PowerToken="";
        }
        if("VoraciousPlant" == Tile)
        {
            VoraciousPlant_PowerToken="";
        }
        if("FireOfEidolon" == Tile)
        {
            FireOfEidolon_DarkRelic="";
        }
    }
    public boolean checkForEnoughPointsBasedOnCurrentPlayer(String tile, String Class)
    {
        if(Class=="Cleric") {
            if(tile=="Pick") {
                if (CurrentPlayer_TotalActionPoints >= Cleric_pickUp_levels[0]) {
                    return true;
                }
            }
            if(tile=="Key") {
                if (CurrentPlayer_TotalActionPoints >= Cleric_pickUp_levels[1]) {
                    return true;
                }
            }
            if(tile=="Scroll") {
                if (CurrentPlayer_TotalActionPoints >= Cleric_pickUp_levels[2]) {
                    return true;
                }
            }
        }
        if(Class=="DarkKnight") {
            if(tile=="Pick") {
                if (CurrentPlayer_TotalActionPoints >= DarkKnight_pickUp_levels[0]) {
                    return true;
                }
            }
            if(tile=="Key") {
                if (CurrentPlayer_TotalActionPoints >= DarkKnight_pickUp_levels[1]) {
                    return true;
                }
            }
            if(tile=="Scroll") {
                if (CurrentPlayer_TotalActionPoints >= DarkKnight_pickUp_levels[2]) {
                    return true;
                }
            }
        }
        if(Class=="Engineer") {
            if(tile=="Pick") {
                if (CurrentPlayer_TotalActionPoints >=Engineer_pickUp_levels[0]) {
                    return true;
                }
            }
            if(tile=="Key") {
                if (CurrentPlayer_TotalActionPoints >= Engineer_pickUp_levels[1]) {
                    return true;
                }
            }
            if(tile=="Scroll") {
                if (CurrentPlayer_TotalActionPoints >= Engineer_pickUp_levels[2]) {
                    return true;
                }
            }
        }
        if(Class=="Geomancer") {
            if(tile=="Pick") {
                if (CurrentPlayer_TotalActionPoints >= Geomancer_pickUp_levels[0]) {
                    return true;
                }
            }
            if(tile=="Key") {
                if (CurrentPlayer_TotalActionPoints >= Geomancer_pickUp_levels[1]) {
                    return true;
                }
            }
            if(tile=="Scroll") {
                if (CurrentPlayer_TotalActionPoints >= Geomancer_pickUp_levels[2]) {
                    return true;
                }
            }
        }
        if(Class=="Paladin") {
            if(tile=="Pick") {
                if (CurrentPlayer_TotalActionPoints >= Paladin_pickUp_levels[0]) {
                    return true;
                }
            }
            if(tile=="Key") {
                if (CurrentPlayer_TotalActionPoints >= Paladin_pickUp_levels[1]) {
                    return true;
                }
            }
            if(tile=="Scroll") {
                if (CurrentPlayer_TotalActionPoints >= Paladin_pickUp_levels[2]) {
                    return true;
                }
            }
        }
        if(Class=="Ranger") {
            if(tile=="Pick") {
                if (CurrentPlayer_TotalActionPoints >= Ranger_pickUp_levels[0]) {
                    return true;
                }
            }
            if(tile=="Key") {
                if (CurrentPlayer_TotalActionPoints >= Ranger_pickUp_levels[1]) {
                    return true;
                }
            }
            if(tile=="Scroll") {
                if (CurrentPlayer_TotalActionPoints >= Ranger_pickUp_levels[2]) {
                    return true;
                }
            }
        }
        if(Class=="Rogue") {
            if(tile=="Pick") {
                if (CurrentPlayer_TotalActionPoints >= Rogue_pickUp_levels[0]) {
                    return true;
                }
            }
            if(tile=="Key") {
                if (CurrentPlayer_TotalActionPoints >= Rogue_pickUp_levels[1]) {
                    return true;
                }
            }
            if(tile=="Scroll") {
                if (CurrentPlayer_TotalActionPoints >= Rogue_pickUp_levels[2]) {
                    return true;
                }
            }
        }
        if(Class=="Sage") {
            if(tile=="Pick") {
                if (CurrentPlayer_TotalActionPoints >= Sage_pickUp_levels[0]) {
                    return true;
                }
            }
            if(tile=="Key") {
                if (CurrentPlayer_TotalActionPoints >= Sage_pickUp_levels[1]) {
                    return true;
                }
            }
            if(tile=="Scroll") {
                if (CurrentPlayer_TotalActionPoints >= Sage_pickUp_levels[2]) {
                    return true;
                }
            }
        }
        if(Class=="Soldier") {
            if(tile=="Pick") {
                if (CurrentPlayer_TotalActionPoints >= Soldier_pickUp_levels[0]) {
                    return true;
                }
            }
            if(tile=="Key") {
                if (CurrentPlayer_TotalActionPoints >= Soldier_pickUp_levels[1]) {
                    return true;
                }
            }
            if(tile=="Scroll") {
                if (CurrentPlayer_TotalActionPoints >= Soldier_pickUp_levels[2]) {
                    return true;
                }
            }
        }
        if(Class=="Swordsman") {
            if(tile=="Pick") {
                if (CurrentPlayer_TotalActionPoints >= Swordsman_pickUp_levels[0]) {
                    return true;
                }
            }
            if(tile=="Key") {
                if (CurrentPlayer_TotalActionPoints >= Swordsman_pickUp_levels[1]) {
                    return true;
                }
            }
            if(tile=="Scroll") {
                if (CurrentPlayer_TotalActionPoints >= Swordsman_pickUp_levels[2]) {
                    return true;
                }
            }
        }
        if(Class=="Warrior") {
            if(tile=="Pick") {
                if (CurrentPlayer_TotalActionPoints >= Warrior_pickUp_levels[0]) {
                    return true;
                }
            }
            if(tile=="Key") {
                if (CurrentPlayer_TotalActionPoints >= Warrior_pickUp_levels[1]) {
                    return true;
                }
            }
            if(tile=="Scroll") {
                if (CurrentPlayer_TotalActionPoints >= Warrior_pickUp_levels[2]) {
                    return true;
                }
            }
        }
        if(Class=="Wizard") {
            if(tile=="Pick") {
                if (CurrentPlayer_TotalActionPoints >= Wizard_pickUp_levels[0]) {
                    return true;
                }
            }
            if(tile=="Key") {
                if (CurrentPlayer_TotalActionPoints >= Wizard_pickUp_levels[1]) {
                    return true;
                }
            }
            if(tile=="Scroll") {
                if (CurrentPlayer_TotalActionPoints >= Wizard_pickUp_levels[2]) {
                    return true;
                }
            }
        }
        return false;
    }
    public void pickUp()
    {
        if(CurrentPlayer_TotalActionPoints>=1) {
            if (CurrentPlayer_Turn == 1) {
                String tile = getTileToken(Player1_Position[0], Player1_Position[1]);
                if (tile == "Fireball" && isBookBroken && isGemBroken && isHeartBroken) {
                    Player1Inventory.add("Fireball");
                    CurrentPlayer_TotalActionPoints -= 1;
                    removeTokenFromTile(refGrid[Player1_Position[0]][Player1_Position[1]]);
                } else if (checkForEnoughPointsBasedOnCurrentPlayer(tile, Player1_Class)) {
                    Player1Inventory.add(tile);
                    reducePointsBasedOnCurrentPlayer(tile, Player1_Class);
                    removeTokenFromTile(refGrid[Player1_Position[0]][Player1_Position[1]]);
                }
            }
            if (CurrentPlayer_Turn == 2) {
                String tile = getTileToken(Player2_Position[0], Player2_Position[1]);
                if (tile == "Fireball" && isBookBroken && isGemBroken && isHeartBroken) {
                    Player2Inventory.add("Fireball");
                    CurrentPlayer_TotalActionPoints -= 1;
                    removeTokenFromTile(refGrid[Player2_Position[0]][Player2_Position[1]]);
                } else if (checkForEnoughPointsBasedOnCurrentPlayer(tile, Player2_Class)) {
                    Player2Inventory.add(tile);
                    reducePointsBasedOnCurrentPlayer(tile, Player2_Class);
                    removeTokenFromTile(refGrid[Player2_Position[0]][Player2_Position[1]]);
                }
            }
            if (CurrentPlayer_Turn == 3) {
                String tile = getTileToken(Player3_Position[0], Player3_Position[1]);
                if (tile == "Fireball" && isBookBroken && isGemBroken && isHeartBroken) {
                    Player3Inventory.add("Fireball");
                    CurrentPlayer_TotalActionPoints -= 1;
                    removeTokenFromTile(refGrid[Player3_Position[0]][Player3_Position[1]]);
                } else if (checkForEnoughPointsBasedOnCurrentPlayer(tile, Player3_Class)) {
                    Player3Inventory.add(tile);
                    reducePointsBasedOnCurrentPlayer(tile, Player3_Class);
                    removeTokenFromTile(refGrid[Player3_Position[0]][Player3_Position[1]]);
                }
            }
            if (CurrentPlayer_Turn == 4) {
                String tile = getTileToken(Player4_Position[0], Player4_Position[1]);
                if (tile == "Fireball" && isBookBroken && isGemBroken && isHeartBroken) {
                    Player4Inventory.add("Fireball");
                    CurrentPlayer_TotalActionPoints -= 1;
                    removeTokenFromTile(refGrid[Player4_Position[0]][Player4_Position[1]]);
                } else if (checkForEnoughPointsBasedOnCurrentPlayer(tile, Player4_Class)) {
                    Player4Inventory.add(tile);
                    reducePointsBasedOnCurrentPlayer(tile, Player4_Class);
                    removeTokenFromTile(refGrid[Player4_Position[0]][Player4_Position[1]]);
                }
            }
            if (CurrentPlayer_Turn == 5) {
                String tile = getTileToken(Player5_Position[0], Player5_Position[1]);
                if (tile == "Fireball" && isBookBroken && isGemBroken && isHeartBroken) {
                    Player5Inventory.add("Fireball");
                    CurrentPlayer_TotalActionPoints -= 1;
                    removeTokenFromTile(refGrid[Player5_Position[0]][Player5_Position[1]]);

                } else if (checkForEnoughPointsBasedOnCurrentPlayer(tile, Player5_Class)) {
                    Player5Inventory.add(tile);
                    reducePointsBasedOnCurrentPlayer(tile, Player5_Class);
                    removeTokenFromTile(refGrid[Player5_Position[0]][Player5_Position[1]]);
                }
            }
            if (CurrentPlayer_Turn == 6) {
                String tile = getTileToken(Player6_Position[0], Player6_Position[1]);
                if (tile == "Fireball" && isBookBroken && isGemBroken && isHeartBroken) {
                    Player6Inventory.add("Fireball");
                    CurrentPlayer_TotalActionPoints -= 1;
                    removeTokenFromTile(refGrid[Player6_Position[0]][Player6_Position[1]]);

                } else if (checkForEnoughPointsBasedOnCurrentPlayer(tile, Player6_Class)) {
                    Player6Inventory.add(tile);
                    reducePointsBasedOnCurrentPlayer(tile, Player6_Class);
                    removeTokenFromTile(refGrid[Player6_Position[0]][Player6_Position[1]]);
                }
            }
        }
        drawActionPoints();
        drawRefBoardAgain();
        drawToken();
        drawHeros();
        addAllInventoryItemsToGiveToCB();
        addAllInventoryItemsToTakeFromCB();
    }
    public void drawActionPoints()
    {
        gc.setFill(Color.BLACK);
        gc.fillRect(OutlineWidth+25+10,OutlineHeight+25-10-5,800, 20);
        gc.strokeText("Action Points: " + CurrentPlayer_TotalActionPoints,OutlineWidth+25+10,OutlineHeight+25);
    }
    public void drawInventoryForPlayers()
    {
        if(NumOfPlayers>=1) {
            String P1 = "";
            for (int i = 0; i < Player1Inventory.size(); i++) {
                P1 += Player1Inventory.get(i) + " ";
            }
            //5/2/2021 Continue here
            gc.setFill(Color.BLACK);
            gc.fillRect(10 + Outline.getWidth() + 20 + .01, 10 + 20 + .01 + 30 + 300-20,1000,20);
            gc.strokeText("P1: " + P1, 10 + Outline.getWidth() + 20 + .01, 10 + 20 + .01 + 30 + 300);
        }
        if(NumOfPlayers>=2) {
            String P2 = "";
            for (int i = 0; i < Player2Inventory.size(); i++) {
                P2 += Player2Inventory.get(i) + " ";
            }
            gc.setFill(Color.BLACK);
            gc.fillRect(10 + Outline.getWidth() + 20 + .01, 10 + 20 + .01 + 50 + 300-20,1000,20);
            gc.strokeText("P2: " + P2, 10 + Outline.getWidth() + 20 + .01, 10 + 20 + .01 + 50 + 300);
        }
        if(NumOfPlayers>=3) {
            String P3 = "";
            for (int i = 0; i < Player3Inventory.size(); i++) {
                P3 += Player3Inventory.get(i) + " ";
            }
            gc.setFill(Color.BLACK);
            gc.fillRect(10 + Outline.getWidth() + 20 + .01, 10 + 20 + .01 + 70 + 300-20,1000,20);
            gc.strokeText("P3: " + P3, 10 + Outline.getWidth() + 20 + .01, 10 + 20 + .01 + 70 + 300);
        }
        if(NumOfPlayers>=4) {
            String P4 = "";
            for (int i = 0; i < Player4Inventory.size(); i++) {
                P4 += Player4Inventory.get(i) + " ";
            }
            gc.setFill(Color.BLACK);
            gc.fillRect(10 + Outline.getWidth() + 20 + .01, 10 + 20 + .01 + 90 + 300-20,1000,20);
            gc.strokeText("P4: " + P4, 10 + Outline.getWidth() + 20 + .01, 10 + 20 + .01 + 90 + 300);
        }
        if(NumOfPlayers>=5) {
            String P5 = "";
            for (int i = 0; i < Player5Inventory.size(); i++) {
                P5 += Player5Inventory.get(i) + " ";
            }
            gc.setFill(Color.BLACK);
            gc.fillRect(10 + Outline.getWidth() + 20 + .01, 10 + 20 + .01 + 110 + 300-20,1000,20);
            gc.strokeText("P5: " + P5, 10 + Outline.getWidth() + 20 + .01, 10 + 20 + .01 + 110 + 300);
        }
        if(NumOfPlayers==6) {
            String P6 = "";
            for (int i = 0; i < Player6Inventory.size(); i++) {
                P6 += Player6Inventory.get(i) + " ";
            }
            gc.setFill(Color.BLACK);
            gc.fillRect(10 + Outline.getWidth() + 20 + .01, 10 + 20 + .01 + 130 + 300-20,1000,20);
            gc.strokeText("P6: " + P6, 10 + Outline.getWidth() + 20 + .01, 10 + 20 + .01 + 130 + 300);
        }
    }
    public void drawToken()
    {
        for (int r = 0; r < refGrid.length; r++) {
            for (int c = 0; c < refGrid[0].length; c++) {
                if((Player1_Position[0]==r && Player1_Position[1]==c) || (Player2_Position[0]==r && Player2_Position[1]==c)||(Player3_Position[0]==r && Player3_Position[1]==c)||(Player4_Position[0]==r && Player4_Position[1]==c)||(Player5_Position[0]==r && Player5_Position[1]==c)||(Player6_Position[0]==r && Player6_Position[1]==c)) {
                    if (refGrid[r][c] == "ArrowTraps" && ArrowTraps_PowerToken=="Key") {
                        gc.drawImage(Key, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                    }
                    if (refGrid[r][c] == "SpikedPit" && SpikedPit_PowerToken=="Key") {
                        gc.drawImage(Key, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                    }
                    if (refGrid[r][c] == "AcidJets" && AcidJets_PowerToken=="Key") {
                        gc.drawImage(Key, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                    }
                    if (refGrid[r][c] == "DenOfSnakes" && DenOfSnakes_PowerToken=="Key") {
                        gc.drawImage(Key, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                    }
                    if (refGrid[r][c] == "FloatingStones" && FloatingStones_PowerToken=="Key") {
                        gc.drawImage(Key, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                    }
                    if (refGrid[r][c] == "LavaLake" && LavaLake_PowerToken=="Key") {
                        gc.drawImage(Key, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                    }
                    if (refGrid[r][c] == "PendulumBlades" && PendulumBlades_PowerToken=="Key") {
                        gc.drawImage(Key, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                    }
                    if (refGrid[r][c] == "DarkSlime" && DarkSlime_PowerToken == "Pick") {
                        gc.drawImage(Pick, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                    }
                    if (refGrid[r][c] == "Dragonling" && Dragonling_PowerToken== "Pick") {
                        gc.drawImage(Pick, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                    }
                    if (refGrid[r][c] == "FelKnight" && FelKnight_PowerToken=="Pick") {
                        gc.drawImage(Pick, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                    }
                    if (refGrid[r][c] == "Minotaur" && Minotaur_PowerToken=="Pick") {
                        gc.drawImage(Pick, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                    }
                    if (refGrid[r][c] == "OgreBrute" && OgreBrute_PowerToken=="Pick") {
                        gc.drawImage(Pick, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                    }
                    if (refGrid[r][c] == "SkeletalGuards" && SkeletalGuards_PowerToken == "Pick") {
                        gc.drawImage(Pick, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                    }
                    if (refGrid[r][c] == "VoraciousPlant" && VoraciousPlant_PowerToken=="Pick") {
                        gc.drawImage(Pick, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                    }
                    if (refGrid[r][c] == "HallOfIllusion" && HallOfIllusion_PowerToken=="Scroll") {
                        gc.drawImage(Scroll, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                    }
                    if (refGrid[r][c] == "LaughingShadow" && LaughingShadow_PowerToken=="Scroll") {
                        gc.drawImage(Scroll, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                    }
                    if (refGrid[r][c] == "MimicChest" && MimicChest_PowerToken=="Scroll") {
                        gc.drawImage(Scroll, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                    }
                    if (refGrid[r][c] == "MindEater" && MindEater_PowerToken=="Scroll") {
                        gc.drawImage(Scroll, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                    }
                    if (refGrid[r][c] == "ParadoxPuzzle" && ParadoxPuzzle_PowerToken=="Scroll") {
                        gc.drawImage(Scroll, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                    }
                    if (refGrid[r][c] == "Psychomancer" && Psychomancer_PowerToken=="Scroll") {
                        gc.drawImage(Scroll, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                    }
                    if (refGrid[r][c] == "SphynxsRiddle" && SphynxsRiddle_PowerToken=="Scroll") {
                        gc.drawImage(Scroll, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                    }
                }
                if(refGrid[r][c]=="VoraxsFocus" && VoraxsFocus_DarkRelic=="Gem")
                {
                    gc.drawImage(Gem_img, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                }
                if(refGrid[r][c]=="VoraxsFocus" && VoraxsFocus_DarkRelic=="BrokenGem")
                {
                    gc.drawImage(BrokenGem_img, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                }
                if(refGrid[r][c]=="VoraxsHeart" && VoraxsHeart_DarkRelic=="Heart")
                {
                    gc.drawImage(Heart_img, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                }
                if(refGrid[r][c]=="VoraxsHeart" && VoraxsHeart_DarkRelic=="BrokenHeart")
                {
                    gc.drawImage(BrokenHeart_img, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                }
                if(refGrid[r][c]=="VoraxsKnowledge" && VoraxsKnowledge_DarkRelic=="Book")
                {
                    gc.drawImage(Book_img, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                }
                if(refGrid[r][c]=="VoraxsKnowledge" && VoraxsKnowledge_DarkRelic=="BrokenBook")
                {
                    gc.drawImage(BrokenBook_img, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                }
                if(refGrid[r][c]=="FireOfEidolon" && FireOfEidolon_DarkRelic=="Fireball")
                {
                    gc.drawImage(Fireball_img, 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths) + (tileLengths / 2), tileLengths / 2, tileLengths / 2);
                }
            }
        }
    }
    public void showOnlyCurrentMoveBttn()
    {
        if(CurrentPlayer_Turn==1){
            Player1_move_btn.setVisible(true);
            Player2_move_btn.setVisible(false);
            Player3_move_btn.setVisible(false);
            Player4_move_btn.setVisible(false);
            Player5_move_btn.setVisible(false);
            Player6_move_btn.setVisible(false);

            Player1PickUp_btn.setVisible(true);
            Player2PickUp_btn.setVisible(false);
            Player3PickUp_btn.setVisible(false);
            Player4PickUp_btn.setVisible(false);
            Player5PickUp_btn.setVisible(false);
            Player6PickUp_btn.setVisible(false);
        }
        if(CurrentPlayer_Turn==2){
            Player1_move_btn.setVisible(false);
            Player2_move_btn.setVisible(true);
            Player3_move_btn.setVisible(false);
            Player4_move_btn.setVisible(false);
            Player5_move_btn.setVisible(false);
            Player6_move_btn.setVisible(false);

            Player1PickUp_btn.setVisible(false);
            Player2PickUp_btn.setVisible(true);
            Player3PickUp_btn.setVisible(false);
            Player4PickUp_btn.setVisible(false);
            Player5PickUp_btn.setVisible(false);
            Player6PickUp_btn.setVisible(false);

        }
        if(CurrentPlayer_Turn==3){
            Player1_move_btn.setVisible(false);
            Player2_move_btn.setVisible(false);
            Player3_move_btn.setVisible(true);
            Player4_move_btn.setVisible(false);
            Player5_move_btn.setVisible(false);
            Player6_move_btn.setVisible(false);

            Player1PickUp_btn.setVisible(false);
            Player2PickUp_btn.setVisible(false);
            Player3PickUp_btn.setVisible(true);
            Player4PickUp_btn.setVisible(false);
            Player5PickUp_btn.setVisible(false);
            Player6PickUp_btn.setVisible(false);

        }
        if(CurrentPlayer_Turn==4){
            Player1_move_btn.setVisible(false);
            Player2_move_btn.setVisible(false);
            Player3_move_btn.setVisible(false);
            Player4_move_btn.setVisible(true);
            Player5_move_btn.setVisible(false);
            Player6_move_btn.setVisible(false);

            Player1PickUp_btn.setVisible(false);
            Player2PickUp_btn.setVisible(false);
            Player3PickUp_btn.setVisible(false);
            Player4PickUp_btn.setVisible(true);
            Player5PickUp_btn.setVisible(false);
            Player6PickUp_btn.setVisible(false);

        }
        if(CurrentPlayer_Turn==5){
            Player1_move_btn.setVisible(false);
            Player2_move_btn.setVisible(false);
            Player3_move_btn.setVisible(false);
            Player4_move_btn.setVisible(false);
            Player5_move_btn.setVisible(true);
            Player6_move_btn.setVisible(false);

            Player1PickUp_btn.setVisible(false);
            Player2PickUp_btn.setVisible(false);
            Player3PickUp_btn.setVisible(false);
            Player4PickUp_btn.setVisible(false);
            Player5PickUp_btn.setVisible(true);
            Player6PickUp_btn.setVisible(false);

        }
        if(CurrentPlayer_Turn==6){
            Player1_move_btn.setVisible(false);
            Player2_move_btn.setVisible(false);
            Player3_move_btn.setVisible(false);
            Player4_move_btn.setVisible(false);
            Player5_move_btn.setVisible(false);
            Player6_move_btn.setVisible(true);

            Player1PickUp_btn.setVisible(false);
            Player2PickUp_btn.setVisible(false);
            Player3PickUp_btn.setVisible(false);
            Player4PickUp_btn.setVisible(false);
            Player5PickUp_btn.setVisible(false);
            Player6PickUp_btn.setVisible(true);

        }
    }
    public int setTurnToNext()
    {
        if(CurrentPlayer_TotalActionPoints==0) {
            if (CurrentPlayer_Turn == NumOfPlayers)
            {
                CurrentPlayer_Turn = 1;
                CurrentPlayer_TotalActionPoints = 3;
                Phase=2;
        //        setVisibilityBasedOnPhase();
                drawRitualStack();
            }
            else {
                CurrentPlayer_Turn++;
                CurrentPlayer_TotalActionPoints = 3;
                Phase=2;
  //              setVisibilityBasedOnPhase();
                drawRitualStack();
            }
            showOnlyCurrentMoveBttn();
            drawActionPoints();
            return CurrentPlayer_Turn;
        }
        else
            {
            showOnlyCurrentMoveBttn();
            drawActionPoints();
            return -1;
        }
    }
    public void drawHeros()
    {
        gc.setStroke(Color.WHITE);
        if(Player1_Position[0]!=-1 && Player1_Position[1]!=-1)
        {
            gc.drawImage(Player1_Piece_Image,10+25+((Player1_Position[1])*tileLengths), 10+25+((Player1_Position[0])*tileLengths),tileLengths/2,tileLengths/2);
            gc.strokeText("1",10+25+((Player1_Position[1])*tileLengths)+(tileLengths/3),10+25+((Player1_Position[0])*tileLengths)+(tileLengths/2)/3);
        }
        if(Player2_Position[0]!=-1 && Player2_Position[1]!=-1)
        {
            gc.drawImage(Player2_Piece_Image,10+25+((Player2_Position[1])*tileLengths)+tileLengths/3, 10+25+((Player2_Position[0])*tileLengths),tileLengths/2,tileLengths/2);
            gc.strokeText("2",10+25+((Player2_Position[1])*tileLengths)+tileLengths/3+(tileLengths/3), 10+25+((Player2_Position[0])*tileLengths)+(tileLengths/2)/3);
        }
        if(Player3_Position[0]!=-1 && Player3_Position[1]!=-1)
        {
            gc.drawImage(Player3_Piece_Image,10+25+((Player3_Position[1])*tileLengths)+tileLengths/3+tileLengths/3, 10+25+((Player3_Position[0])*tileLengths),tileLengths/2,tileLengths/2);
            gc.strokeText("3", 10+25+((Player3_Position[1])*tileLengths)+tileLengths/3+tileLengths/3 +(tileLengths/3), 10+25+((Player3_Position[0])*tileLengths)+(tileLengths/2)/3);
        }
        if(Player4_Position[0]!=-1 && Player4_Position[1]!=-1)
        {
            gc.drawImage(Player4_Piece_Image,10+25+((Player4_Position[1])*tileLengths), 10+25+((Player4_Position[0])*tileLengths)+tileLengths/3,tileLengths/2,tileLengths/2);
            gc.strokeText("4",10+25+((Player4_Position[1])*tileLengths)+(tileLengths/3), 10+25+((Player4_Position[0])*tileLengths)+tileLengths/3+(tileLengths/2)/3);
        }
        if(Player5_Position[0]!=-1 && Player5_Position[1]!=-1)
        {
            gc.drawImage(Player5_Piece_Image,10+25+((Player5_Position[1])*tileLengths)+tileLengths/3, 10+25+((Player5_Position[0])*tileLengths)+tileLengths/3,tileLengths/2,tileLengths/2);
            gc.strokeText("5",10+25+((Player5_Position[1])*tileLengths)+tileLengths/3+(tileLengths/3), 10+25+((Player5_Position[0])*tileLengths)+tileLengths/3+(tileLengths/2)/3);
        }
        if(Player6_Position[0]!=-1 && Player6_Position[1]!=-1)
        {
            gc.drawImage(Player6_Piece_Image,10+25+((Player6_Position[1])*tileLengths)+tileLengths/3+tileLengths/3, 10+25+((Player6_Position[0])*tileLengths)+tileLengths/3,tileLengths/2,tileLengths/2);
            gc.strokeText("6",10+25+((Player6_Position[1])*tileLengths)+tileLengths/3+tileLengths/3+(tileLengths/3), 10+25+((Player6_Position[0])*tileLengths)+tileLengths/3+(tileLengths/2)/3);
        }

        if(P1Down==true)
        {
            gc.setStroke(Color.RED);
            gc.strokeRect(10+25+((Player1_Position[1])*tileLengths), 10+25+((Player1_Position[0])*tileLengths),tileLengths/2,tileLengths/2);
        }
        if(P2Down==true)
        {
            gc.setStroke(Color.RED);
            gc.strokeRect(10+25+((Player2_Position[1])*tileLengths)+tileLengths/3, 10+25+((Player2_Position[0])*tileLengths),tileLengths/2,tileLengths/2);
        }
        if(P3Down==true)
        {
            gc.setStroke(Color.RED);
            gc.strokeRect(10+25+((Player3_Position[1])*tileLengths)+tileLengths/3+tileLengths/3, 10+25+((Player3_Position[0])*tileLengths),tileLengths/2,tileLengths/2);
        }
        if(P4Down==true)
        {
            gc.setStroke(Color.RED);
            gc.strokeRect(10+25+((Player4_Position[1])*tileLengths), 10+25+((Player4_Position[0])*tileLengths)+tileLengths/3,tileLengths/2,tileLengths/2);
        }
        if(P5Down==true)
        {
            gc.setStroke(Color.RED);
            gc.strokeRect(10+25+((Player5_Position[1])*tileLengths)+tileLengths/3, 10+25+((Player5_Position[0])*tileLengths)+tileLengths/3,tileLengths/2,tileLengths/2);
        }
        if(P6Down==true)
        {
            gc.setStroke(Color.RED);
            gc.strokeRect(10+25+((Player6_Position[1])*tileLengths)+tileLengths/3+tileLengths/3, 10+25+((Player6_Position[0])*tileLengths)+tileLengths/3,tileLengths/2,tileLengths/2);
        }

    }
    public boolean checkIfDoorsConnectForMoving(int player, String direction)
    {
        int r=-1;
        int c=-1;
        if(player==1){r=Player1_Position[0];c=Player1_Position[1];}
        if(player==2){r=Player2_Position[0];c=Player2_Position[1];}
        if(player==3){r=Player3_Position[0];c=Player3_Position[1];}
        if(player==4){r=Player4_Position[0];c=Player4_Position[1];}
        if(player==5){r=Player5_Position[0];c=Player5_Position[1];}
        if(player==6){r=Player6_Position[0];c=Player6_Position[1];}
        int[] loc = new int[2];
        loc[0]=r;
        loc[1]=c;
                if(refGrid[r][c]!=null)
                {
                    int[] locOfPieceOnBoard = {r,c};
                    int[] locOfPieceOnBoardUpOne = {locOfPieceOnBoard[0]-1, locOfPieceOnBoard[1]};
                    int[] locOfPieceOnBoardDownOne = {locOfPieceOnBoard[0]+1, locOfPieceOnBoard[1]};
                    int[] locOfPieceOnBoardRightOne = {locOfPieceOnBoard[0], locOfPieceOnBoard[1]+1};
                    int[] locOfPieceOnBoardLeftOne = {locOfPieceOnBoard[0], locOfPieceOnBoard[1]-1};
                    if(refGrid[r][c]=="Vestibule")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            /*if(AcidJets_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(Vestibule_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }

                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(AcidJets_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(Vestibule_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(AcidJets_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(Vestibule_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(AcidJets_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(Vestibule_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="AcidJets")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            /*if(AcidJets_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(AcidJets_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }

                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(AcidJets_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(AcidJets_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(AcidJets_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(AcidJets_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(AcidJets_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(AcidJets_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="ArrowTraps")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            /*if(ArrowTraps_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(ArrowTraps_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }

                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(ArrowTraps_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(ArrowTraps_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(ArrowTraps_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(ArrowTraps_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(ArrowTraps_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(ArrowTraps_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="DarkSlime")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            /*if(DarkSlime_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(DarkSlime_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }

                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(DarkSlime_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(DarkSlime_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(DarkSlime_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(DarkSlime_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(DarkSlime_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(DarkSlime_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="DenOfSnakes")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            /*if(DenOfSnakes_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(DenOfSnakes_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }

                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(DenOfSnakes_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(DenOfSnakes_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(DenOfSnakes_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(DenOfSnakes_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(DenOfSnakes_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(DenOfSnakes_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="Dragonling")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            /*if(Dragonling_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(Dragonling_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(Dragonling_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(Dragonling_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(Dragonling_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(Dragonling_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(Dragonling_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(Dragonling_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="FelKnight")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            /*if(FelKnight_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(FelKnight_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(FelKnight_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(FelKnight_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(FelKnight_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(FelKnight_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(FelKnight_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(FelKnight_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="FireOfEidolon")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            /*if(FireOfEidolon_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(FireOfEidolon_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(FireOfEidolon_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(FireOfEidolon_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(FireOfEidolon_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(FireOfEidolon_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(FireOfEidolon_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(FireOfEidolon_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="FloatingStones")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            /*if(FloatingStones_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(FloatingStones_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(FloatingStones_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(FloatingStones_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(FloatingStones_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(FloatingStones_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(FloatingStones_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(FloatingStones_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="HallOfIllusion")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            /*if(HallOfIllusion_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(HallOfIllusion_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(HallOfIllusion_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(HallOfIllusion_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(HallOfIllusion_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(HallOfIllusion_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(HallOfIllusion_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(HallOfIllusion_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="LaughingShadow")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            /*if(LaughingShadow_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(LaughingShadow_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(LaughingShadow_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(LaughingShadow_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(LaughingShadow_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(LaughingShadow_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(LaughingShadow_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(LaughingShadow_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="LavaLake")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            /*if(LavaLake_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(LavaLake_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(LavaLake_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(LavaLake_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(LavaLake_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(LavaLake_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(LavaLake_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(LavaLake_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="MimicChest")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            /*if(MimicChest_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(MimicChest_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(MimicChest_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(MimicChest_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(MimicChest_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(MimicChest_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(MimicChest_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(MimicChest_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="MindEater")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            /*if(MindEater_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(MindEater_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(MindEater_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(MindEater_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(MindEater_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(MindEater_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(MindEater_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(MindEater_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="Minotaur")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            /*if(Minotaur_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(Minotaur_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(Minotaur_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(Minotaur_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(Minotaur_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(Minotaur_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(Minotaur_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(Minotaur_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="NewExit")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            /*if(NewExit_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(NewExit_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(NewExit_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(NewExit_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(NewExit_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(NewExit_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(NewExit_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(NewExit_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="OgreBrute")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            /*if(OgreBrute_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(OgreBrute_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(OgreBrute_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(OgreBrute_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(OgreBrute_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(OgreBrute_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(OgreBrute_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(OgreBrute_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="ParadoxPuzzle")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            /*if(ParadoxPuzzle_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(ParadoxPuzzle_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(ParadoxPuzzle_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(ParadoxPuzzle_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(ParadoxPuzzle_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(ParadoxPuzzle_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(ParadoxPuzzle_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(ParadoxPuzzle_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="PendulumBlades")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            /*if(PendulumBlades_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(PendulumBlades_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(PendulumBlades_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(PendulumBlades_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(PendulumBlades_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(PendulumBlades_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(PendulumBlades_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(PendulumBlades_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="Psychomancer")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            /*if(Psychomancer_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(Psychomancer_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(Psychomancer_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(Psychomancer_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(Psychomancer_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(Psychomancer_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(Psychomancer_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(Psychomancer_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="SecretPassageX")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            /*if(SecretPassageX_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(SecretPassageX_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(SecretPassageX_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(SecretPassageX_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(SecretPassageX_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(SecretPassageX_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(SecretPassageX_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(SecretPassageX_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="SecretPassageY")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            /*if(SecretPassageX_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(SecretPassageY_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(SecretPassageX_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(SecretPassageY_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(SecretPassageX_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(SecretPassageY_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(SecretPassageX_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(SecretPassageY_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="SkeletalGuards")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            /*if(SkeletalGuards_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(SkeletalGuards_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(SkeletalGuards_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(SkeletalGuards_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(SkeletalGuards_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(SkeletalGuards_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(SkeletalGuards_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(SkeletalGuards_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="SphynxsRiddle")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            /*if(SphynxsRiddle_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(SphynxsRiddle_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(SphynxsRiddle_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(SphynxsRiddle_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(SphynxsRiddle_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(SphynxsRiddle_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(SphynxsRiddle_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(SphynxsRiddle_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="SpikedPit")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            //->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                            /*if(SpikedPit_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(SpikedPit_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(SpikedPit_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(SpikedPit_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(SpikedPit_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(SpikedPit_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(SpikedPit_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(SpikedPit_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="Vestibule")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            //->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                            /*if(Vestibule_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            System.out.println("Vestibule_doorplacements.contains(\"up\")==true"+ (Vestibule_doorplacements.contains("up")==true));
                            System.out.println("stringToDoorPlacement(stackOfChamberTiles.get(0)).contains(\"down\")==true"+ (stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true));
                            if(Vestibule_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            System.out.println(Vestibule_doorplacements.toString());
                            System.out.println(stringToDoorPlacement(stackOfChamberTiles.get(0)).toString());
                            /*if(Vestibule_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(Vestibule_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(Vestibule_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(Vestibule_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(Vestibule_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(Vestibule_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="VoraciousPlant")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            //->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                            /*if(VoraciousPlant_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(VoraciousPlant_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(VoraciousPlant_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(VoraciousPlant_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(VoraciousPlant_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(VoraciousPlant_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(VoraciousPlant_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(VoraciousPlant_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="VoraxsFocus")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            //->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                            /*if(VoraxsFocus_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(VoraxsFocus_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(VoraxsFocus_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(VoraxsFocus_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(VoraxsFocus_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(VoraxsFocus_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(VoraxsFocus_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(VoraxsFocus_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="VoraxsHeart")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            //->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                            /*if(VoraxsHeart_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(VoraxsHeart_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(VoraxsHeart_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(VoraxsHeart_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(VoraxsHeart_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(VoraxsHeart_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(VoraxsHeart_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(VoraxsHeart_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="VoraxsKnowledge")
                    {
                        if(direction=="up" && refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]!=null)                       {
                            //->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                            /*if(VoraxsKnowledge_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(VoraxsKnowledge_doorplacements.contains("up")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardUpOne[0]][locOfPieceOnBoardUpOne[1]]).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="down" && refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]!=null)
                        {
                            /*if(VoraxsKnowledge_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(VoraxsKnowledge_doorplacements.contains("down")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardDownOne[0]][locOfPieceOnBoardDownOne[1]]).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="right" && refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]!=null)
                        {
                            /*if(VoraxsKnowledge_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(VoraxsKnowledge_doorplacements.contains("right")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardRightOne[0]][locOfPieceOnBoardRightOne[1]]).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if(direction=="left" && refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]!=null)
                        {
                            /*if(VoraxsKnowledge_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(VoraxsKnowledge_doorplacements.contains("left")==true && stringToDoorPlacement(refGrid[locOfPieceOnBoardLeftOne[0]][locOfPieceOnBoardLeftOne[1]]).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                }
                return false;
    }


    public boolean moveIntoRoom(int player,int r, int c)
    {
        if(CurrentPlayer_TotalActionPoints>=1) {
            if (player == 1 && refGrid[r][c] != null) {
                if((r==Player1_Position[0]-1 && c==Player1_Position[1] && checkIfDoorsConnectForMoving(1,"up")) || (r==Player1_Position[0]+1 && c==Player1_Position[1] && checkIfDoorsConnectForMoving(1,"down")) || (r==Player1_Position[0] && c==Player1_Position[1]-1 && checkIfDoorsConnectForMoving(1,"left")) || (r==Player1_Position[0] && c==Player1_Position[1]+1 && checkIfDoorsConnectForMoving(1,"right"))) {
                    CurrentPlayer_TotalActionPoints -= 1;
                    Player1_Position[0] = r;
                    Player1_Position[1] = c;
                    drawActionPoints();
                    addAllInventoryItemsToTakeFromCB();
                    addAllInventoryItemsToGiveToCB();
                    return true;
                }
            }
            else if (player == 2 && refGrid[r][c] != null) {
                if((r==Player2_Position[0]-1 && c==Player2_Position[1] && checkIfDoorsConnectForMoving(2,"up")) || (r==Player2_Position[0]+1 && c==Player2_Position[1] && checkIfDoorsConnectForMoving(2,"down")) || (r==Player2_Position[0] && c==Player2_Position[1]-1 && checkIfDoorsConnectForMoving(2,"left")) || (r==Player2_Position[0] && c==Player2_Position[1]+1 && checkIfDoorsConnectForMoving(2,"right"))) {
                    CurrentPlayer_TotalActionPoints -= 1;
                    Player2_Position[0] = r;
                    Player2_Position[1] = c;
                    drawActionPoints();
                    addAllInventoryItemsToTakeFromCB();
                    addAllInventoryItemsToGiveToCB();
                    return true;
                }
            }
            else if (player == 3 && refGrid[r][c] != null) {
                if((r==Player3_Position[0]-1 && c==Player3_Position[1] && checkIfDoorsConnectForMoving(3,"up")) || (r==Player3_Position[0]+1 && c==Player3_Position[1] && checkIfDoorsConnectForMoving(3,"down")) || (r==Player3_Position[0] && c==Player3_Position[1]-1 && checkIfDoorsConnectForMoving(3,"left")) || (r==Player3_Position[0] && c==Player3_Position[1]+1 && checkIfDoorsConnectForMoving(3,"right"))) {
                    CurrentPlayer_TotalActionPoints -= 1;
                    Player3_Position[0] = r;
                    Player3_Position[1] = c;
                    drawActionPoints();
                    addAllInventoryItemsToTakeFromCB();
                    addAllInventoryItemsToGiveToCB();
                    return true;

                }
            }
            else if (player == 4 && refGrid[r][c] != null) {
                if((r==Player4_Position[0]-1 && c==Player4_Position[1] && checkIfDoorsConnectForMoving(4,"up")) || (r==Player4_Position[0]+1 && c==Player4_Position[1] && checkIfDoorsConnectForMoving(4,"down")) || (r==Player4_Position[0] && c==Player4_Position[1]-1 && checkIfDoorsConnectForMoving(4,"left")) || (r==Player4_Position[0] && c==Player4_Position[1]+1 && checkIfDoorsConnectForMoving(4,"right"))) {
                    CurrentPlayer_TotalActionPoints -= 1;
                    Player4_Position[0] = r;
                    Player4_Position[1] = c;
                    drawActionPoints();
                    addAllInventoryItemsToTakeFromCB();
                    addAllInventoryItemsToGiveToCB();
                    return true;
                }
            } else if (player == 5 && refGrid[r][c] != null) {
                if((r==Player5_Position[0]-1 && c==Player5_Position[1] && checkIfDoorsConnectForMoving(5,"up")) || (r==Player5_Position[0]+1 && c==Player5_Position[1] && checkIfDoorsConnectForMoving(5,"down")) || (r==Player5_Position[0] && c==Player5_Position[1]-1 && checkIfDoorsConnectForMoving(5,"left")) || (r==Player5_Position[0] && c==Player5_Position[1]+1 && checkIfDoorsConnectForMoving(5,"right"))) {
                    CurrentPlayer_TotalActionPoints -= 1;
                    Player5_Position[0] = r;
                    Player5_Position[1] = c;
                    drawActionPoints();
                    addAllInventoryItemsToTakeFromCB();
                    addAllInventoryItemsToGiveToCB();
                    return true;
                }
            } else if (player == 6 && refGrid[r][c] != null) {
                if((r==Player6_Position[0]-1 && c==Player6_Position[1] && checkIfDoorsConnectForMoving(6,"up")) || (r==Player6_Position[0]+1 && c==Player6_Position[1] && checkIfDoorsConnectForMoving(6,"down")) || (r==Player6_Position[0] && c==Player6_Position[1]-1 && checkIfDoorsConnectForMoving(6,"left")) || (r==Player6_Position[0] && c==Player6_Position[1]+1 && checkIfDoorsConnectForMoving(6,"right"))) {
                    CurrentPlayer_TotalActionPoints -= 1;
                    Player6_Position[0] = r;
                    Player6_Position[1] = c;
                    drawActionPoints();
                    addAllInventoryItemsToTakeFromCB();
                    addAllInventoryItemsToGiveToCB();
                    return true;
                }
            }
        }
        drawRefBoardAgain();
        setTurnToNext();
        PlayerOnSameTileSetVisible();
        return false;
    }
    public void drawCultist()
    {
        for (int r = 0; r < refGrid.length; r++) {
            for (int c = 0; c < refGrid[0].length; c++) {
                if((refGrid[r][c]=="AcidJets"    && AcidJets_CultistTokenCount>=1)  ||
                (refGrid[r][c]=="ArrowTraps"     && ArrowTrap_CultistTokenCount>=1) ||
                (refGrid[r][c]=="VoraxsKnowledge"&& BlueEvent_CultistTokenCount>=1) ||
                (refGrid[r][c]=="DarkSlime"      && DarkSlime_CultistTokenCount>=1) ||
                (refGrid[r][c]=="DenOfSnakes"    && DenOfSnakes_CultistTokenCount>=1) ||
                (refGrid[r][c]=="Dragonling"     && Dragonling_CultistTokenCount>=1) ||
                (refGrid[r][c]=="FelKnight"      && FelKnight_CultistTokenCount>=1) ||
                (refGrid[r][c]=="FloatingStones" && FloatingStones_CultistTokenCount>=1) ||
                (refGrid[r][c]=="VoraxsFocus"    && GreenEvent_CultistTokenCount>=1) ||
                (refGrid[r][c]=="HallOfIllusion" && HallOfIllusions_CultistTokenCount>=1) ||
                (refGrid[r][c]=="LaughingShadow" && LaughingShadow_CultistTokenCount>=1) ||
                (refGrid[r][c]=="LavaLake"       && LavaLake_CultistTokenCount>=1) ||
                (refGrid[r][c]=="MimicChest"     && MimicChest_CultistTokenCount>=1) ||
                (refGrid[r][c]=="MindEater"      && MindEater_CultistTokenCount>=1) ||
                (refGrid[r][c]=="Minotaur"       && Minotaur_CultistTokenCount>=1) ||
                (refGrid[r][c]=="OgreBrute"      && OgreBrute_CultistTokenCount>=1) ||
                (refGrid[r][c]=="ParadoxPuzzle"  && ParadoxPuzzle_CultistTokenCount>=1) ||
                (refGrid[r][c]=="PendulumBlades" && PendulumBlades_CultistTokenCount>=1) ||
                (refGrid[r][c]=="Psychomancer"   && Psychomancer_CultistTokenCount>=1) ||
                (refGrid[r][c]=="VoraxsHeart"    && RedEvent_CultistTokenCount>=1) ||
                (refGrid[r][c]=="SkeletalGuards" && SkeletalGuards_CultistTokenCount>=1) ||
                (refGrid[r][c]=="SphynxsRiddle"  && SphynxsRiddle_CultistTokenCount>=1) ||
                (refGrid[r][c]=="SpikedPit"      && SpikedPit_CultistTokenCount>=1) ||
                (refGrid[r][c]=="VoraciousPlant" && VoraciousPlant_CultistTokenCount>=1))
                {
                    gc.drawImage(new Image("File:CultistToken.png", tileLengths, tileLengths, false, false), 10 + 25 + ((c) * tileLengths) + (tileLengths / 2) - (tileLengths / 2) / 2, 10 + 25 + ((r) * tileLengths), tileLengths / 2, tileLengths / 2);
                }
            }
        }
    }
    public void drawRefBoardAgain()
    {
        gc.drawImage(Outline, 10,10,Outline.getWidth(), Outline.getHeight());
        gc.drawImage(Vestibule_img,10+25+(4*tileLengths),10+25+(4*tileLengths),tileLengths,tileLengths);
        gc.fillRect(10+Outline.getWidth()+20+.01,10+20+.01, tileLengths-.01, tileLengths-.01);
        gc.strokeRect(10+Outline.getWidth()+20,10+20,tileLengths,tileLengths);
        FillEmptySpace();
        for (int r = 0; r < refGrid.length; r++) {
            for (int c = 0; c < refGrid[0].length; c++) {
                if(refGrid[r][c]!=null)
                {
                    gc.drawImage(stringToImage(refGrid[r][c]),10+25+((c)*tileLengths), 10+25+((r)*tileLengths),tileLengths,tileLengths);
                }
            }
        }
        drawToken();
        drawCultist();
        drawHeros();
    }
    public boolean VerifyIfDoorsConnect(int locR,int locC)
    {
        int[] loc = {locR,locC};
        for (int r = 0; r < refGrid.length ; r++) {
            for (int c = 0; c < refGrid[0].length; c++) {
                if(refGrid[r][c]!=null)
                {
                    int[] locOfPieceOnBoard = {r,c};
                    int[] locOfPieceOnBoardUpOne = {locOfPieceOnBoard[0]-1, locOfPieceOnBoard[1]};
                    int[] locOfPieceOnBoardDownOne = {locOfPieceOnBoard[0]+1, locOfPieceOnBoard[1]};
                    int[] locOfPieceOnBoardRightOne = {locOfPieceOnBoard[0], locOfPieceOnBoard[1]+1};
                    int[] locOfPieceOnBoardLeftOne = {locOfPieceOnBoard[0], locOfPieceOnBoard[1]-1};
                    if(refGrid[r][c]=="AcidJets")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            if(AcidJets_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }
                            if(AcidJets_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }

                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(AcidJets_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(AcidJets_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(AcidJets_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(AcidJets_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(AcidJets_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(AcidJets_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="ArrowTraps")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            /*if(ArrowTraps_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(ArrowTraps_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }

                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(ArrowTraps_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(ArrowTraps_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(ArrowTraps_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(ArrowTraps_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(ArrowTraps_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(ArrowTraps_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="DarkSlime")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            /*if(DarkSlime_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(DarkSlime_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }

                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(DarkSlime_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(DarkSlime_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(DarkSlime_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(DarkSlime_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(DarkSlime_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(DarkSlime_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="DenOfSnakes")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            /*if(DenOfSnakes_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(DenOfSnakes_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }

                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(DenOfSnakes_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(DenOfSnakes_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(DenOfSnakes_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(DenOfSnakes_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(DenOfSnakes_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(DenOfSnakes_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="Dragonling")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            /*if(Dragonling_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(Dragonling_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(Dragonling_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(Dragonling_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(Dragonling_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(Dragonling_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(Dragonling_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(Dragonling_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="FelKnight")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            /*if(FelKnight_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(FelKnight_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(FelKnight_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(FelKnight_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(FelKnight_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(FelKnight_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(FelKnight_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(FelKnight_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="FireOfEidolon")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            /*if(FireOfEidolon_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(FireOfEidolon_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(FireOfEidolon_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(FireOfEidolon_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(FireOfEidolon_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(FireOfEidolon_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(FireOfEidolon_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(FireOfEidolon_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="FloatingStones")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            /*if(FloatingStones_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(FloatingStones_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(FloatingStones_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(FloatingStones_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(FloatingStones_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(FloatingStones_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(FloatingStones_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(FloatingStones_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="HallOfIllusion")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            /*if(HallOfIllusion_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(HallOfIllusion_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(HallOfIllusion_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(HallOfIllusion_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(HallOfIllusion_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(HallOfIllusion_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(HallOfIllusion_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(HallOfIllusion_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="LaughingShadow")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            /*if(LaughingShadow_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(LaughingShadow_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(LaughingShadow_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(LaughingShadow_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(LaughingShadow_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(LaughingShadow_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(LaughingShadow_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(LaughingShadow_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="LavaLake")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            /*if(LavaLake_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(LavaLake_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(LavaLake_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(LavaLake_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(LavaLake_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(LavaLake_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(LavaLake_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(LavaLake_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="MimicChest")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            /*if(MimicChest_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(MimicChest_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(MimicChest_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(MimicChest_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(MimicChest_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(MimicChest_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(MimicChest_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(MimicChest_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="MindEater")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            /*if(MindEater_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(MindEater_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(MindEater_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(MindEater_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(MindEater_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(MindEater_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(MindEater_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(MindEater_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="Minotaur")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            /*if(Minotaur_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(Minotaur_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(Minotaur_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(Minotaur_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(Minotaur_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(Minotaur_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(Minotaur_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(Minotaur_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="NewExit")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            /*if(NewExit_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(NewExit_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(NewExit_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(NewExit_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(NewExit_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(NewExit_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(NewExit_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(NewExit_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="OgreBrute")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            /*if(OgreBrute_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(OgreBrute_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(OgreBrute_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(OgreBrute_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(OgreBrute_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(OgreBrute_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(OgreBrute_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(OgreBrute_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="ParadoxPuzzle")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            /*if(ParadoxPuzzle_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(ParadoxPuzzle_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(ParadoxPuzzle_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(ParadoxPuzzle_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(ParadoxPuzzle_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(ParadoxPuzzle_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(ParadoxPuzzle_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(ParadoxPuzzle_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="PendulumBlades")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            /*if(PendulumBlades_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(PendulumBlades_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(PendulumBlades_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(PendulumBlades_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(PendulumBlades_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(PendulumBlades_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(PendulumBlades_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(PendulumBlades_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="Psychomancer")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            /*if(Psychomancer_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(Psychomancer_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(Psychomancer_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(Psychomancer_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(Psychomancer_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(Psychomancer_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(Psychomancer_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(Psychomancer_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="SecretPassageX")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            /*if(SecretPassageX_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(SecretPassageX_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(SecretPassageX_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(SecretPassageX_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(SecretPassageX_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(SecretPassageX_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(SecretPassageX_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(SecretPassageX_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="SecretPassageY")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            /*if(SecretPassageX_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(SecretPassageY_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(SecretPassageX_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(SecretPassageY_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(SecretPassageX_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(SecretPassageY_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(SecretPassageX_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(SecretPassageY_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="SkeletalGuards")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            /*if(SkeletalGuards_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(SkeletalGuards_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(SkeletalGuards_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(SkeletalGuards_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(SkeletalGuards_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(SkeletalGuards_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(SkeletalGuards_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(SkeletalGuards_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="SphynxsRiddle")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            /*if(SphynxsRiddle_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(SphynxsRiddle_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(SphynxsRiddle_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(SphynxsRiddle_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(SphynxsRiddle_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(SphynxsRiddle_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(SphynxsRiddle_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(SphynxsRiddle_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="SpikedPit")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            //->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                            /*if(SpikedPit_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(SpikedPit_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(SpikedPit_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(SpikedPit_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(SpikedPit_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(SpikedPit_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(SpikedPit_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(SpikedPit_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="Vestibule")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            //->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                            /*if(Vestibule_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            System.out.println("Vestibule_doorplacements.contains(\"up\")==true"+ (Vestibule_doorplacements.contains("up")==true));
                            System.out.println("stringToDoorPlacement(stackOfChamberTiles.get(0)).contains(\"down\")==true"+ (stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true));
                            if(Vestibule_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            System.out.println(Vestibule_doorplacements.toString());
                            System.out.println(stringToDoorPlacement(stackOfChamberTiles.get(0)).toString());
                            /*if(Vestibule_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(Vestibule_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(Vestibule_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(Vestibule_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(Vestibule_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(Vestibule_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="VoraciousPlant")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            //->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                            /*if(VoraciousPlant_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(VoraciousPlant_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(VoraciousPlant_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(VoraciousPlant_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(VoraciousPlant_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(VoraciousPlant_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(VoraciousPlant_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(VoraciousPlant_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="VoraxsFocus")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            //->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                            /*if(VoraxsFocus_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(VoraxsFocus_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(VoraxsFocus_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(VoraxsFocus_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(VoraxsFocus_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(VoraxsFocus_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(VoraxsFocus_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(VoraxsFocus_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="VoraxsHeart")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            //->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                            /*if(VoraxsHeart_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(VoraxsHeart_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(VoraxsHeart_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(VoraxsHeart_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(VoraxsHeart_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(VoraxsHeart_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(VoraxsHeart_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(VoraxsHeart_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                    if(refGrid[r][c]=="VoraxsKnowledge")
                    {
                        if((loc[0] == locOfPieceOnBoardUpOne[0] && loc[1] == locOfPieceOnBoardUpOne[1]))
                        {
                            //->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                            /*if(VoraxsKnowledge_doorplacements.contains("up")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")!=true)
                            {
                                return false;
                            }*/
                            if(VoraxsKnowledge_doorplacements.contains("up")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("down")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardDownOne[0] && loc[1] == locOfPieceOnBoardDownOne[1]))
                        {
                            /*if(VoraxsKnowledge_doorplacements.contains("down")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")!=true)
                            {
                                return false;
                            }*/
                            if(VoraxsKnowledge_doorplacements.contains("down")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("up")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardRightOne[0] && loc[1] == locOfPieceOnBoardRightOne[1]))
                        {
                            /*if(VoraxsKnowledge_doorplacements.contains("right")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")!=true)
                            {
                                return false;
                            }*/
                            if(VoraxsKnowledge_doorplacements.contains("right")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("left")==true)
                            {
                                return true;
                            }
                        }
                        if((loc[0] == locOfPieceOnBoardLeftOne[0] && loc[1] == locOfPieceOnBoardLeftOne[1]))
                        {
                            /*if(VoraxsKnowledge_doorplacements.contains("left")!=true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")!=true)
                            {
                                return false;
                            }*/
                            if(VoraxsKnowledge_doorplacements.contains("left")==true && stringToDoorPlacement(stackOfChamberTiles.get(0)).contains("right")==true)
                            {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    public ArrayList<String> stringToDoorPlacement(String room)
    {
        if("AcidJets".equals(room)) {
            return AcidJets_doorplacements;
        }
        if("ArrowTraps".equals(room)) {
            return ArrowTraps_doorplacements;
        }
        if("Blank".equals(room)) {
            return Blank_doorplacements;
        }
        if("DarkSlime".equals(room)) {
            return DarkSlime_doorplacements;
        }
        if("DenOfSnakes".equals(room)) {
            return DenOfSnakes_doorplacements;
        }
        if("Dragonling".equals(room)) {
            return Dragonling_doorplacements;
        }
        if("FelKnight".equals(room)) {
            return FelKnight_doorplacements;
        }
        if("FireOfEidolon".equals(room)) {
            return FireOfEidolon_doorplacements;
        }
        if("FloatingStones".equals(room)) {
            return FloatingStones_doorplacements;
        }
        if("HallOfIllusion".equals(room)) {
            return HallOfIllusion_doorplacements;
        }
        if("LaughingShadow".equals(room)) {
            return LaughingShadow_doorplacements;
        }
        if("LavaLake".equals(room)) {
            return LavaLake_doorplacements;
        }
        if("MimicChest".equals(room)) {
            return MimicChest_doorplacements;
        }
        if("MindEater".equals(room)) {
            return MindEater_doorplacements;
        }
        if("Minotaur".equals(room)) {
            return Minotaur_doorplacements;
        }
        if("NewExit".equals(room)) {
            return NewExit_doorplacements;
        }
        if("OgreBrute".equals(room)) {
            return OgreBrute_doorplacements;
        }
        if("ParadoxPuzzle".equals(room)) {
            return ParadoxPuzzle_doorplacements;
        }
        if("PendulumBlades" .equals(room)) {
            return PendulumBlades_doorplacements;
        }
        if("Psychomancer".equals(room)) {
            return Psychomancer_doorplacements;
        }
        if("SecretPassageX".equals(room)) {
            return SecretPassageX_doorplacements;
        }
        if("SecretPassageY".equals(room)) {
            return SecretPassageY_doorplacements;
        }
        if("SkeletalGuards".equals(room)) {
            return SkeletalGuards_doorplacements;
        }
        if("SphynxsRiddle".equals(room)) {
            return SphynxsRiddle_doorplacements;
        }
        if("SpikedPit".equals(room)) {
            return SpikedPit_doorplacements;
        }
        if("Vestibule".equals(room)) {
            return Vestibule_doorplacements;
        }
        if("VoraciousPlant".equals(room)) {
            return VoraciousPlant_doorplacements;
        }
        if("VoraxsFocus".equals(room)) {
            return VoraxsFocus_doorplacements;
        }
        if("VoraxsHeart".equals(room)) {
            return VoraxsHeart_doorplacements;
        }
        if("VoraxsKnowledge".equals(room)) {
            return VoraxsKnowledge_doorplacements;
        }
        return null;
    }
    public void rotateDoorPlacement()
    {
        //Clockwise
        if("AcidJets"== stackOfChamberTiles.get(0)) {
            ArrayList<String> ChamberCopy = (ArrayList<String>)AcidJets_doorplacements.clone();
                if (ChamberCopy.indexOf("up") != -1) {
                AcidJets_doorplacements.set(ChamberCopy.indexOf("up"),"right");
            }
            if(ChamberCopy.indexOf("down") != -1)
            {
                AcidJets_doorplacements.set(ChamberCopy.indexOf("down"),"left");
            }
            if(ChamberCopy.indexOf("right") != -1)
            {
                AcidJets_doorplacements.set(ChamberCopy.indexOf("right"),"down");
            }
            if(ChamberCopy.indexOf("left") != -1)
            {
                AcidJets_doorplacements.set(ChamberCopy.indexOf("left"),"up");
            }
        }
        if("ArrowTraps"== stackOfChamberTiles.get(0))
        {
            ArrayList<String> ChamberCopy = (ArrayList<String>)ArrowTraps_doorplacements.clone();

            if (ChamberCopy.indexOf("up") != -1) {
                ArrowTraps_doorplacements.set(ChamberCopy.indexOf("up"),"right");
            }
            if(ChamberCopy.indexOf("down") != -1)
            {
                ArrowTraps_doorplacements.set(ChamberCopy.indexOf("down"),"left");
            }
            if(ChamberCopy.indexOf("right") != -1)
            {
                ArrowTraps_doorplacements.set(ChamberCopy.indexOf("right"),"down");
            }
            if(ChamberCopy.indexOf("left") != -1)
            {
                ArrowTraps_doorplacements.set(ChamberCopy.indexOf("left"),"up");
            }
        }
        if("Blank"== stackOfChamberTiles.get(0))
        {
            ArrayList<String> ChamberCopy = (ArrayList<String>)Blank_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1) {
                Blank_doorplacements.set(ChamberCopy.indexOf("up"),"right");
            }
            if(ChamberCopy.indexOf("down") != -1)
            {
                Blank_doorplacements.set(ChamberCopy.indexOf("down"),"left");
            }
            if(ChamberCopy.indexOf("right") != -1)
            {
                Blank_doorplacements.set(ChamberCopy.indexOf("right"),"down");
            }
            if(ChamberCopy.indexOf("left") != -1)
            {
                Blank_doorplacements.set(ChamberCopy.indexOf("left"),"up");
            }
        }
        if("DarkSlime" == stackOfChamberTiles.get(0))
        {
            ArrayList<String> ChamberCopy = (ArrayList<String>)DarkSlime_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1) {
                DarkSlime_doorplacements.set(ChamberCopy.indexOf("up"),"right");
            }
            if(ChamberCopy.indexOf("down") != -1)
            {
                DarkSlime_doorplacements.set(ChamberCopy.indexOf("down"),"left");
            }
            if(ChamberCopy.indexOf("right") != -1)
            {
                DarkSlime_doorplacements.set(ChamberCopy.indexOf("right"),"down");
            }
            if(ChamberCopy.indexOf("left") != -1)
            {
                DarkSlime_doorplacements.set(ChamberCopy.indexOf("left"),"up");
            }
        }
        if("DenOfSnakes" == stackOfChamberTiles.get(0))
        {
            ArrayList<String> ChamberCopy = (ArrayList<String>)DenOfSnakes_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1) {
                DenOfSnakes_doorplacements.set(ChamberCopy.indexOf("up"),"right");
            }
            if(ChamberCopy.indexOf("down") != -1)
            {
                DenOfSnakes_doorplacements.set(ChamberCopy.indexOf("down"),"left");
            }
            if(ChamberCopy.indexOf("right") != -1)
            {
                DenOfSnakes_doorplacements.set(ChamberCopy.indexOf("right"),"down");
            }
            if(ChamberCopy.indexOf("left") != -1)
            {
                DenOfSnakes_doorplacements.set(ChamberCopy.indexOf("left"),"up");
            }
        }
        if("Dragonling"  == stackOfChamberTiles.get(0))
        {
            ArrayList<String> ChamberCopy = (ArrayList<String>)Dragonling_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1) {
                Dragonling_doorplacements.set(ChamberCopy.indexOf("up"),"right");
            }
            if(ChamberCopy.indexOf("down") != -1)
            {
                Dragonling_doorplacements.set(ChamberCopy.indexOf("down"),"left");
            }
            if(ChamberCopy.indexOf("right") != -1)
            {
                Dragonling_doorplacements.set(ChamberCopy.indexOf("right"),"down");
            }
            if(ChamberCopy.indexOf("left") != -1)
            {
                Dragonling_doorplacements.set(ChamberCopy.indexOf("left"),"up");
            }
        }
        if("FelKnight"== stackOfChamberTiles.get(0)) {
            ArrayList<String> ChamberCopy = (ArrayList<String>)FelKnight_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1) {
                FelKnight_doorplacements.set(ChamberCopy.indexOf("up"), "right");
            }
            if (ChamberCopy.indexOf("right") != -1) {
                FelKnight_doorplacements.set(ChamberCopy.indexOf("right"), "down");
            }
            if (ChamberCopy.indexOf("down") != -1) {
                FelKnight_doorplacements.set(ChamberCopy.indexOf("down"), "left");
            }
            if (ChamberCopy.indexOf("left") != -1) {
                FelKnight_doorplacements.set(ChamberCopy.indexOf("left"), "up");
            }
        }

        if("FireOfEidolon"  == stackOfChamberTiles.get(0)) {
            ArrayList<String> ChamberCopy = (ArrayList<String>)FireOfEidolon_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1) {
                FireOfEidolon_doorplacements.set(ChamberCopy.indexOf("up"), "right");
            }
            if (ChamberCopy.indexOf("right") != -1) {
                FireOfEidolon_doorplacements.set(ChamberCopy.indexOf("right"), "down");
            };
            if (ChamberCopy.indexOf("down") != -1) {
                FireOfEidolon_doorplacements.set(ChamberCopy.indexOf("down"), "left");
            }
            if (ChamberCopy.indexOf("left") != -1) {
                FireOfEidolon_doorplacements.set(ChamberCopy.indexOf("left"), "up");
            }
        }
        if("FloatingStones" == stackOfChamberTiles.get(0)) {
            ArrayList<String> ChamberCopy = (ArrayList<String>)FloatingStones_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1) {
                FloatingStones_doorplacements.set(ChamberCopy.indexOf("up"), "right");
            }
            if (ChamberCopy.indexOf("right") != -1) {
                FloatingStones_doorplacements.set(ChamberCopy.indexOf("right"), "down");
            }
            if (ChamberCopy.indexOf("down") != -1) {
                FloatingStones_doorplacements.set(ChamberCopy.indexOf("down"), "left");
            }
            if (ChamberCopy.indexOf("left") != -1) {
                FloatingStones_doorplacements.set(ChamberCopy.indexOf("left"), "up");
            }        }

        if("HallOfIllusion" == stackOfChamberTiles.get(0)) {
            ArrayList<String> ChamberCopy = (ArrayList<String>)HallOfIllusion_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1) {
                HallOfIllusion_doorplacements.set(ChamberCopy.indexOf("up"), "right");
            }
            if (ChamberCopy.indexOf("right") != -1) {
                HallOfIllusion_doorplacements.set(ChamberCopy.indexOf("right"), "down");
            }
            if (ChamberCopy.indexOf("down") != -1) {
                HallOfIllusion_doorplacements.set(ChamberCopy.indexOf("down"), "left");
            }
            if (ChamberCopy.indexOf("left") != -1) {
                HallOfIllusion_doorplacements.set(ChamberCopy.indexOf("left"), "up");
            }        }

        if("LaughingShadow" == stackOfChamberTiles.get(0)) {
            ArrayList<String> ChamberCopy = (ArrayList<String>)LaughingShadow_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1) {
                LaughingShadow_doorplacements.set(ChamberCopy.indexOf("up"), "right");
            }
            if (ChamberCopy.indexOf("right") != -1) {
                LaughingShadow_doorplacements.set(ChamberCopy.indexOf("right"), "down");
            }
            if (ChamberCopy.indexOf("down") != -1) {
                LaughingShadow_doorplacements.set(ChamberCopy.indexOf("down"), "left");
            }
            if (ChamberCopy.indexOf("left") != -1) {
                LaughingShadow_doorplacements.set(ChamberCopy.indexOf("left"), "up");
            }        }

        if("LavaLake"       == stackOfChamberTiles.get(0)) {
            ArrayList<String> ChamberCopy = (ArrayList<String>)LavaLake_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1)
            {
                LavaLake_doorplacements.set(ChamberCopy.indexOf("up"), "right");
            }
            if (ChamberCopy.indexOf("right") != -1)
            {
                LavaLake_doorplacements.set(ChamberCopy.indexOf("right"), "down");
            }
            if (ChamberCopy.indexOf("down") != -1)
            {
                LavaLake_doorplacements.set(ChamberCopy.indexOf("down"), "left");
            }
            if (ChamberCopy.indexOf("left") != -1)
            {
                LavaLake_doorplacements.set(ChamberCopy.indexOf("left"), "up");
            }
        }

        if("MimicChest"     == stackOfChamberTiles.get(0)) {
            ArrayList<String> ChamberCopy = (ArrayList<String>)MimicChest_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1) {
                MimicChest_doorplacements.set(ChamberCopy.indexOf("up"), "right");
            }
            if (ChamberCopy.indexOf("right") != -1) {
                MimicChest_doorplacements.set(ChamberCopy.indexOf("right"), "down");
            }
            if (ChamberCopy.indexOf("down") != -1) {
                MimicChest_doorplacements.set(ChamberCopy.indexOf("down"), "left");
            }
            if (ChamberCopy.indexOf("left") != -1) {
                MimicChest_doorplacements.set(ChamberCopy.indexOf("left"), "up");
            }
        }

        if("MindEater"      == stackOfChamberTiles.get(0)) {
            ArrayList<String> ChamberCopy = (ArrayList<String>)MindEater_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1) {
                MindEater_doorplacements.set(ChamberCopy.indexOf("up"), "right");
            }
            if (ChamberCopy.indexOf("right") != -1) {
                MindEater_doorplacements.set(ChamberCopy.indexOf("right"), "down");
            }
            if (ChamberCopy.indexOf("down") != -1) {
                MindEater_doorplacements.set(ChamberCopy.indexOf("down"), "left");
            }
            if (ChamberCopy.indexOf("left") != -1) {
                MindEater_doorplacements.set(ChamberCopy.indexOf("left"), "up");
            }
        }

        if("Minotaur"       == stackOfChamberTiles.get(0)) {
            ArrayList<String> ChamberCopy = (ArrayList<String>)Minotaur_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1) {
                Minotaur_doorplacements.set(ChamberCopy.indexOf("up"), "right");
            }
            if (ChamberCopy.indexOf("right") != -1) {
                Minotaur_doorplacements.set(ChamberCopy.indexOf("right"), "down");
            }
            if (ChamberCopy.indexOf("down") != -1) {
                Minotaur_doorplacements.set(ChamberCopy.indexOf("down"), "left");
            }
            if (ChamberCopy.indexOf("left") != -1) {
                Minotaur_doorplacements.set(ChamberCopy.indexOf("left"), "up");
            }
        }

        if("NewExit"        == stackOfChamberTiles.get(0)) {
            ArrayList<String> ChamberCopy = (ArrayList<String>)NewExit_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1) {
                NewExit_doorplacements.set(ChamberCopy.indexOf("up"), "right");
            }
            if (ChamberCopy.indexOf("right") != -1) {
                NewExit_doorplacements.set(ChamberCopy.indexOf("right"), "down");
            }
            if (ChamberCopy.indexOf("down") != -1) {
                NewExit_doorplacements.set(ChamberCopy.indexOf("down"), "left");
            }
            if (ChamberCopy.indexOf("left") != -1) {
                NewExit_doorplacements.set(ChamberCopy.indexOf("left"), "up");
            } }

        if("OgreBrute"      == stackOfChamberTiles.get(0)) {
            ArrayList<String> ChamberCopy = (ArrayList<String>)OgreBrute_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1) {
                OgreBrute_doorplacements.set(ChamberCopy.indexOf("up"), "right");
            }
            if (ChamberCopy.indexOf("right") != -1) {
                OgreBrute_doorplacements.set(ChamberCopy.indexOf("right"), "down");
            }
            if (ChamberCopy.indexOf("down") != -1) {
                OgreBrute_doorplacements.set(ChamberCopy.indexOf("down"), "left");
            }
            if (ChamberCopy.indexOf("left") != -1) {
                OgreBrute_doorplacements.set(ChamberCopy.indexOf("left"), "up");
            }   }

        if("ParadoxPuzzle"  == stackOfChamberTiles.get(0)) {
            ArrayList<String> ChamberCopy = (ArrayList<String>)ParadoxPuzzle_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1) {
                ParadoxPuzzle_doorplacements.set(ChamberCopy.indexOf("up"), "right");
            }
            if (ChamberCopy.indexOf("right") != -1) {
                ParadoxPuzzle_doorplacements.set(ChamberCopy.indexOf("right"), "down");
            }
            if (ChamberCopy.indexOf("down") != -1) {
                ParadoxPuzzle_doorplacements.set(ChamberCopy.indexOf("down"), "left");
            }
            if (ChamberCopy.indexOf("left") != -1) {
                ParadoxPuzzle_doorplacements.set(ChamberCopy.indexOf("left"), "up");
            }
        }

        if("PendulumBlades" == stackOfChamberTiles.get(0)) {
            ArrayList<String> ChamberCopy = (ArrayList<String>)PendulumBlades_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1) {
                PendulumBlades_doorplacements.set(ChamberCopy.indexOf("up"), "right");
            }
            if (ChamberCopy.indexOf("right") != -1) {
                PendulumBlades_doorplacements.set(ChamberCopy.indexOf("right"), "down");
            }
            if (ChamberCopy.indexOf("down") != -1) {
                PendulumBlades_doorplacements.set(ChamberCopy.indexOf("down"), "left");
            }
            if (ChamberCopy.indexOf("left") != -1) {
                PendulumBlades_doorplacements.set(ChamberCopy.indexOf("left"), "up");
            }        }

        if("Psychomancer"   == stackOfChamberTiles.get(0)) {
            ArrayList<String> ChamberCopy = (ArrayList<String>)Psychomancer_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1) {
                Psychomancer_doorplacements.set(ChamberCopy.indexOf("up"), "right");
            }
            if (ChamberCopy.indexOf("right") != -1) {
                Psychomancer_doorplacements.set(ChamberCopy.indexOf("right"), "down");
            }
            if (ChamberCopy.indexOf("down") != -1) {
                Psychomancer_doorplacements.set(ChamberCopy.indexOf("down"), "left");
            }
            if (ChamberCopy.indexOf("left") != -1) {
                Psychomancer_doorplacements.set(ChamberCopy.indexOf("left"), "up");
            }
        }

        if("SecretPassageX" == stackOfChamberTiles.get(0)) {
            ArrayList<String> ChamberCopy = (ArrayList<String>)SecretPassageX_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1) {
                SecretPassageX_doorplacements.set(ChamberCopy.indexOf("up"), "right");
            }
            if (ChamberCopy.indexOf("right") != -1) {
                SecretPassageX_doorplacements.set(ChamberCopy.indexOf("right"), "down");
            }
            if (ChamberCopy.indexOf("down") != -1) {
                SecretPassageX_doorplacements.set(ChamberCopy.indexOf("down"), "left");
            };
            if (ChamberCopy.indexOf("left") != -1) {
                SecretPassageX_doorplacements.set(ChamberCopy.indexOf("left"), "up");
            }
        }

        if("SecretPassageY" == stackOfChamberTiles.get(0)) {
            ArrayList<String> ChamberCopy = (ArrayList<String>)SecretPassageY_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1) {
                SecretPassageY_doorplacements.set(ChamberCopy.indexOf("up"), "right");
            }
            if (ChamberCopy.indexOf("right") != -1) {
                SecretPassageY_doorplacements.set(ChamberCopy.indexOf("right"), "down");
            }
            if (ChamberCopy.indexOf("down") != -1) {
                SecretPassageY_doorplacements.set(ChamberCopy.indexOf("down"), "left");
            }
            if (ChamberCopy.indexOf("left") != -1) {
                SecretPassageY_doorplacements.set(ChamberCopy.indexOf("left"), "up");
            }
        }

        if("SkeletalGuards" == stackOfChamberTiles.get(0)) {
            ArrayList<String> ChamberCopy = (ArrayList<String>)SkeletalGuards_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1) {
                SkeletalGuards_doorplacements.set(ChamberCopy.indexOf("up"), "right");
            }
            if (ChamberCopy.indexOf("right") != -1) {
                SkeletalGuards_doorplacements.set(ChamberCopy.indexOf("right"), "down");
            }
            if (ChamberCopy.indexOf("down") != -1) {
                SkeletalGuards_doorplacements.set(ChamberCopy.indexOf("down"), "left");
            }
            if (ChamberCopy.indexOf("left") != -1) {
                SkeletalGuards_doorplacements.set(ChamberCopy.indexOf("left"), "up");
            }
        }

        if("SphynxsRiddle"  == stackOfChamberTiles.get(0)) {
            ArrayList<String> ChamberCopy = (ArrayList<String>)SphynxsRiddle_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1) {
                SphynxsRiddle_doorplacements.set(ChamberCopy.indexOf("up"), "right");
            }
            if (ChamberCopy.indexOf("right") != -1) {
                SphynxsRiddle_doorplacements.set(ChamberCopy.indexOf("right"), "down");
            }
            if (ChamberCopy.indexOf("down") != -1) {
                SphynxsRiddle_doorplacements.set(ChamberCopy.indexOf("down"), "left");
            }
            if (ChamberCopy.indexOf("left") != -1) {
                SphynxsRiddle_doorplacements.set(ChamberCopy.indexOf("left"), "up");
            }
        }

        if("SpikedPit"  == stackOfChamberTiles.get(0)) {
            ArrayList<String> ChamberCopy = (ArrayList<String>)SpikedPit_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1) {
                SpikedPit_doorplacements.set(ChamberCopy.indexOf("up"), "right");
            }
            if (ChamberCopy.indexOf("right") != -1) {
                SpikedPit_doorplacements.set(ChamberCopy.indexOf("right"), "down");
            }
            if (ChamberCopy.indexOf("down") != -1) {
                SpikedPit_doorplacements.set(ChamberCopy.indexOf("down"), "left");
            }
            if (ChamberCopy.indexOf("left") != -1) {
                SpikedPit_doorplacements.set(ChamberCopy.indexOf("left"), "up");
            }
        }

        if("VoraciousPlant" == stackOfChamberTiles.get(0)) {
            ArrayList<String> ChamberCopy = (ArrayList<String>)VoraciousPlant_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1) {
                VoraciousPlant_doorplacements.set(ChamberCopy.indexOf("up"), "right");
            }
            if (ChamberCopy.indexOf("right") != -1) {
                VoraciousPlant_doorplacements.set(ChamberCopy.indexOf("right"), "down");
            }
            if (ChamberCopy.indexOf("down") != -1) {
                VoraciousPlant_doorplacements.set(ChamberCopy.indexOf("down"), "left");
            };
            if (ChamberCopy.indexOf("left") != -1) {
                VoraciousPlant_doorplacements.set(ChamberCopy.indexOf("left"), "up");
            }
        }

        if("VoraxsFocus"    == stackOfChamberTiles.get(0)) {
            ArrayList<String> ChamberCopy = (ArrayList<String>)VoraxsFocus_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1) {
                VoraxsFocus_doorplacements.set(ChamberCopy.indexOf("up"), "right");
            }
            if (ChamberCopy.indexOf("right") != -1) {
                VoraxsFocus_doorplacements.set(ChamberCopy.indexOf("right"), "down");
            }
            if (ChamberCopy.indexOf("down") != -1) {
                VoraxsFocus_doorplacements.set(ChamberCopy.indexOf("down"), "left");
            }
            if (ChamberCopy.indexOf("left") != -1) {
                VoraxsFocus_doorplacements.set(ChamberCopy.indexOf("left"), "up");
            }
        }

        if("VoraxsHeart"    == stackOfChamberTiles.get(0)) {
            ArrayList<String> ChamberCopy = (ArrayList<String>)VoraxsHeart_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1) {
                VoraxsHeart_doorplacements.set(ChamberCopy.indexOf("up"), "right");
            }
            if (ChamberCopy.indexOf("right") != -1) {
                VoraxsHeart_doorplacements.set(ChamberCopy.indexOf("right"), "down");
            }
            if (ChamberCopy.indexOf("down") != -1) {
                VoraxsHeart_doorplacements.set(ChamberCopy.indexOf("down"), "left");
            }
            if (ChamberCopy.indexOf("left") != -1) {
                VoraxsHeart_doorplacements.set(ChamberCopy.indexOf("left"), "up");
            }
        }

        if("VoraxsKnowledge"== stackOfChamberTiles.get(0)) {
            ArrayList<String> ChamberCopy = (ArrayList<String>)VoraxsKnowledge_doorplacements.clone();
            if (ChamberCopy.indexOf("up") != -1) {
                VoraxsKnowledge_doorplacements.set(ChamberCopy.indexOf("up"), "right");
            }
            if (ChamberCopy.indexOf("right") != -1) {
                VoraxsKnowledge_doorplacements.set(ChamberCopy.indexOf("right"), "down");
            }
            if (ChamberCopy.indexOf("down") != -1) {
                VoraxsKnowledge_doorplacements.set(ChamberCopy.indexOf("down"), "left");
            }
            if (ChamberCopy.indexOf("left") != -1) {
                VoraxsKnowledge_doorplacements.set(ChamberCopy.indexOf("left"), "up");
            }
        }
    }
    public void FillEmptySpace()
    {
        gc.setFill(Color.GRAY);
        for (int r = 0; r <9; r++) {
            for (int c = 0; c <9; c++) {
                if(refGrid[r][c]==null)
                {
                    gc.fillRect(10+25+((c)*tileLengths), 10+25+((r)*tileLengths),tileLengths,tileLengths);
                }
            }
        }
        gc.setStroke(Color.WHITE);
        for (int r = 0; r <9; r++) {
            for (int c = 0; c <9; c++) {

                    gc.strokeRect(10+25+((c)*tileLengths), 10+25+((r)*tileLengths),tileLengths,tileLengths);
            }
        }
    }
    public ArrayList<int[]> getAvailablePlacements()
    {
        ArrayList<int[]> placements = new ArrayList<>();
        for (int r = 0; r < refGrid.length; r++) {
            for (int c = 0; c < refGrid[0].length; c++) {
                if(refGrid[r][c]!=null)
                {
                    //up
                    if((r-1>=0 && r-1<=8) && refGrid[r-1][c]==null)
                    {
                        int[] place = {r-1,c};
                        placements.add(place);
                    }
                    //down
                    if((r+1>=0 && r+1<=8) && refGrid[r+1][c]==null)
                    {
                        int[] place = {r+1,c};
                        placements.add(place);
                    }
                    //left
                    if((c-1>=0 && c-1<=8) && refGrid[r][c-1]==null)
                    {
                        int[] place = {r,c-1};
                        placements.add(place);
                    }
                    //right
                    if((c+1>=0 && c+1<=8) && refGrid[r][c+1]==null)
                    {
                        int[] place = {r,c+1};
                        placements.add(place);
                    }
                }

            }
        }
        return placements;
    }
    public Image getRulePage(int i) {
        if (i == 1) {
            return One;
        }
        if (i == 2) {
            return Two;
        }
        if (i == 3) {
            return Three;
        }
        if (i == 4) {
            return Four;
        }
        if (i == 5) {
            return Five;
        }
        if (i == 6) {
            return Six;
        }
        if (i == 7) {
            return Seven;
        }
        if (i == 8) {
            return Eight;
        }
        if (i == 9) {
            return Nine;
        }
        if (i == 10) {
            return Ten;
        }
        if (i == 11) {
            return Eleven;
        }
        if (i == 12) {
            return Twelve;
        }
        if (i == 13) {
            return Thirteen;
        }
        if (i == 14) {
            return Fourteen;
        }
        if (i == 15) {
            return Fifteen;
        }
        if (i == 16) {
            return Sixteen;
        }
        if (i == 17) {
            return Seventeen;
        }
        if (i == 18) {
            return Eighteen;
        }
        if (i == 19) {
            return Nineteen;
        }
        if (i == 20) {
            return Twenty;
        }
        if (i == 21) {
            return Twentyone;
        }
        if (i == 22) {
            return Twentytwo;
        }
        if (i == 23) {
            return Twentythree;
        }
        if (i == 24) {
            return Twentyfour;
        }
        if (i == 25) {
            return Twentyfive;
        }
        if (i == 26) {
            return Twentysix;
        }
        return null;

    }
    public void setRotateImage(Image RotatedImage)
    {
        if(Vestibule_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            Vestibule_img = RotatedImage;
        }
        if(AcidJets_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            AcidJets_img = RotatedImage;
        }
        if(ArrowTraps_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            ArrowTraps_img= RotatedImage;
        }
        if(Blank_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            Blank_img= RotatedImage;
        }
        if(DarkSlime_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            DarkSlime_img= RotatedImage;
        }
        if(DenOfSnakes_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            DenOfSnakes_img= RotatedImage;
        }
        if(Dragonling_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            Dragonling_img= RotatedImage;
        }
        if(FelKnight_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            FelKnight_img= RotatedImage;
        }
        if(FireOfEidolon_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            FireOfEidolon_img= RotatedImage;
        }
        if(FloatingStones_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            FloatingStones_img= RotatedImage;
        }
        if(HallOfIllusion_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            HallOfIllusion_img= RotatedImage;
        }
        if(LaughingShadow_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            LaughingShadow_img= RotatedImage;
        }
        if(LavaLake_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            LavaLake_img= RotatedImage;
        }
        if(MimicChest_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            MimicChest_img= RotatedImage;
        }
        if(MindEater_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            MindEater_img= RotatedImage;
        }
        if(Minotaur_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            Minotaur_img= RotatedImage;
        }
        if(NewExit_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            NewExit_img= RotatedImage;
        }
        if(OgreBrute_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            OgreBrute_img= RotatedImage;
        }
        if(ParadoxPuzzle_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            ParadoxPuzzle_img= RotatedImage;
        }
        if(PendulumBlades_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            PendulumBlades_img= RotatedImage;
        }
        if(Psychomancer_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            Psychomancer_img= RotatedImage;
        }
        if(SecretPassageX_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            SecretPassageX_img= RotatedImage;
        }
        if(SecretPassageY_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            SecretPassageY_img= RotatedImage;
        }
        if(SkeletalGuards_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            SkeletalGuards_img= RotatedImage;
        }
        if(SphynxsRiddle_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            SphynxsRiddle_img= RotatedImage;
        }
        if(SpikedPit_img == (stringToImage(stackOfChamberTiles.get(0))))
        {
            SpikedPit_img = RotatedImage;
        }
        if(VoraciousPlant_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            VoraciousPlant_img= RotatedImage;
        }
        if(VoraxsFocus_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            VoraxsFocus_img= RotatedImage;
        }
        if(VoraxsHeart_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            VoraxsHeart_img= RotatedImage;
        }
        if(VoraxsKnowledge_img==(stringToImage(stackOfChamberTiles.get(0))))
        {
            VoraxsKnowledge_img= RotatedImage;
        }
    }
    public boolean checkifClickIsInOutline(double x, double y)
    {
        if(x>10+25+(0*tileLengths) && y>10+25+(0*tileLengths)) {
            if (x<10+25+(0*tileLengths)+tileLengths*9 && y<10+25+(0*tileLengths)+tileLengths*9)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
    public int[] getRowAndCol(double x, double y)
    {
        int[] rowAndCol = new int[2];
        if(checkifClickIsInOutline(x,y)==true)
        {
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {
                    if(x > 10+25+(c)*tileLengths && y>10+25+((r)*tileLengths))
                    {
                        if(x < 10+25+((c)*tileLengths)+tileLengths && y < 10+25+((r)*tileLengths)+tileLengths)
                        {
                            rowAndCol[0] = r;
                            rowAndCol[1] = c;
                            return rowAndCol;
                        }
                    }
                }
            }
            rowAndCol[0] = -1;
            rowAndCol[1] = -1;
            return rowAndCol;
        }
        else
            {
            rowAndCol[0] = -1;
            rowAndCol[1] = -1;
            return rowAndCol;
        }
    }
    public ArrayList<String> randomizeStack() {
        Collections.shuffle(stackOfChamberTiles);
        Collections.shuffle(StackOfRitualCards);
        return stackOfChamberTiles;
    }
    public void AddToPlayerClasses(ComboBox<String> comboBox)
    {
        comboBox.getItems().addAll("Cleric", "DarkKnight", "Engineer", "Geomancer", "Paladin", "Ranger", "Rogue", "Sage", "Soldier", "Swordsman", "Warrior", "Wizard");
    }
    public void highlightCard(GraphicsContext g, int r, int c)
    {
        g.setFill(Color.YELLOW);
        g.fillRect(10+25+((c)*tileLengths),10+25+((r)*tileLengths),tileLengths,tileLengths);
    }
    public void drawRules(GraphicsContext g, Image rulePage)
    {
        g.setFill(Color.BLACK);
        g.fillRect(0,0,canvasWidth,canvasHeight);
        g.drawImage(rulePage,0,0,canvasWidth,canvasHeight);
    }
    public void drawPlayerClasses(GraphicsContext g, int player, String Card)
    {
        if(Card.equalsIgnoreCase("Cleric"))
        {
            g.drawImage(Cleric,25+(player-1)*tileLengths*2+10, 10+25+(OutlineHeight)+25,tileLengths*2,tileLengths*2);
        }
        if(Card.equalsIgnoreCase("DarkKnight"))
        {
            g.drawImage(DarkKnight,25+(player-1)*tileLengths*2+10, 10+25+(OutlineHeight)+25,tileLengths*2,tileLengths*2);
        }
        if(Card.equalsIgnoreCase("Engineer"))
        {
            g.drawImage(Engineer,25+(player-1)*tileLengths*2+10, 10+25+(OutlineHeight)+25,tileLengths*2,tileLengths*2);
        }
        if(Card.equalsIgnoreCase("Geomancer"))
        {
            g.drawImage(Geomancer,25+(player-1)*tileLengths*2+10, 10+25+(OutlineHeight)+25,tileLengths*2,tileLengths*2);
        }
        if(Card.equalsIgnoreCase("Paladin"))
        {
            g.drawImage(Paladin,25+(player-1)*tileLengths*2+10, 10+25+(OutlineHeight)+25,tileLengths*2,tileLengths*2);
        }
        if(Card.equalsIgnoreCase("Ranger"))
        {
            g.drawImage(Ranger,25+(player-1)*tileLengths*2+10, 10+25+(OutlineHeight)+25,tileLengths*2,tileLengths*2);
        }
        if(Card.equalsIgnoreCase("Rogue"))
        {
            g.drawImage(Rogue,25+(player-1)*tileLengths*2+10, 10+25+(OutlineHeight)+25,tileLengths*2,tileLengths*2);
        }
        if(Card.equalsIgnoreCase("Sage"))
        {
            g.drawImage(Sage,25+(player-1)*tileLengths*2+10, 10+25+(OutlineHeight)+25,tileLengths*2,tileLengths*2);
        }
        if(Card.equalsIgnoreCase("Soldier"))
        {
            g.drawImage(Soldier,25+(player-1)*tileLengths*2+10, 10+25+(OutlineHeight)+25,tileLengths*2,tileLengths*2);
        }
        if(Card.equalsIgnoreCase("Swordsman"))
        {
            g.drawImage(Swordsman,25+(player-1)*tileLengths*2+10, 10+25+(OutlineHeight)+25,tileLengths*2,tileLengths*2);
        }
        if(Card.equalsIgnoreCase("Warrior"))
        {
            g.drawImage(Warrior,25+(player-1)*tileLengths*2+10, 10+25+(OutlineHeight)+25,tileLengths*2,tileLengths*2);
        }
        if(Card.equalsIgnoreCase("Wizard"))
        {
            g.drawImage(Wizard,25+(player-1)*tileLengths*2+10, 10+25+(OutlineHeight)+25,tileLengths*2,tileLengths*2);
        }
    }
    public void drawCardToTheSideForPlacement(GraphicsContext g)
    {
            Image card_img = stringToImage(stackOfChamberTiles.get(0));
            g.drawImage(card_img, (10 + Outline.getWidth() + 20) + (tileLengths) + 20, 20, 200, 200);
    }
    public void drawCardOnBoard(GraphicsContext g, Image card_img, int r, int c)
    {
        g.drawImage(card_img, 10+25+((c)*tileLengths), 10+25+((r)*tileLengths),tileLengths,tileLengths);
    }
    public Image stringToImage(String imageName)
    {
        if("Vestibule".equals(imageName))
            return Vestibule_img;
        if("AcidJets".equals(imageName))
            return AcidJets_img;
        if("ArrowTraps".equals(imageName))
            return ArrowTraps_img;
        if("Blank".equals(imageName))
            return Blank_img;
        if("DarkSlime".equals(imageName))
            return DarkSlime_img;
        if("DenOfSnakes".equals(imageName))
            return DenOfSnakes_img;
        if("Dragonling".equals(imageName))
            return Dragonling_img;
        if("FelKnight".equals(imageName))
            return FelKnight_img;
        if("FireOfEidolon".equals(imageName))
            return FireOfEidolon_img;
        if("FloatingStones".equals(imageName))
            return FloatingStones_img;
        if("HallOfIllusion".equals(imageName))
            return HallOfIllusion_img;
        if("LaughingShadow".equals(imageName))
            return LaughingShadow_img;
        if("LavaLake".equals(imageName))
            return LavaLake_img;
        if("MimicChest".equals(imageName))
            return MimicChest_img;
        if("MindEater".equals(imageName))
            return MindEater_img;
        if("Minotaur".equals(imageName))
            return Minotaur_img;
        if("NewExit".equals(imageName))
            return NewExit_img;
        if("OgreBrute".equals(imageName))
            return OgreBrute_img;
        if("ParadoxPuzzle".equals(imageName))
            return ParadoxPuzzle_img;
        if("PendulumBlades".equals(imageName))
            return PendulumBlades_img;
        if("Psychomancer".equals(imageName))
            return Psychomancer_img;
        if("SecretPassageX".equals(imageName))
            return SecretPassageX_img;
        if("SecretPassageY".equals(imageName))
            return SecretPassageY_img;
        if("SkeletalGuards".equals(imageName))
            return SkeletalGuards_img;
        if("SphynxsRiddle".equals(imageName))
            return SphynxsRiddle_img;
        if("SpikedPit".equals(imageName))
            return SpikedPit_img;
        if("VoraciousPlant".equals(imageName))
            return VoraciousPlant_img;
        if("VoraxsFocus".equals(imageName))
            return VoraxsFocus_img;
        if("VoraxsHeart".equals(imageName))
            return VoraxsHeart_img;
        if("VoraxsKnowledge".equals(imageName))
            return VoraxsKnowledge_img;
        return null;
    }
    public void drawClickToPlayPage(GraphicsContext g)
    {
        g.setFill(Color.BLACK);
        g.fillRect(0,0,canvasWidth,canvasHeight);
        g.setStroke(Color.WHITE);
        g.strokeText("Click Anywhere to Play \n Make Sure to select your number of players",400,400);
    }
    public void drawSelectingPlayerAndClassesPage(GraphicsContext g)
    {
        g.setFill(Color.BLACK);
        g.fillRect(0,0,canvasWidth,canvasHeight);
        gc.setStroke(Color.WHITE);
        gc.strokeText("Click anywhere on the black screen to continue",400,400);


    }
    public void drawPlayPage(GraphicsContext g)
    {
        g.setFill(Color.DIMGREY);
        g.fillRect(0,0,canvasWidth,canvasHeight);
        g.setStroke(Color.WHITE);
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                g.strokeRect(10+25+((c)*tileLengths),10+25+((r)*tileLengths),tileLengths,tileLengths);
            }
        }

        g.drawImage(Outline, 10,10,Outline.getWidth(), Outline.getHeight());
        g.drawImage(Vestibule_img,10+25+(4*tileLengths),10+25+(4*tileLengths),tileLengths,tileLengths);

        g.drawImage(NormalThreatLevel_img,10+Outline.getWidth()+20+.01,10+20+.01+480, tileLengths*2, tileLengths*3);

        g.setStroke(Color.YELLOW);
        g.setFill(Color.DIMGREY);
        g.fillRect(10+Outline.getWidth()+20+.01,10+20+.01, tileLengths-.01, tileLengths-.01);
        g.strokeRect(10+Outline.getWidth()+20,10+20,tileLengths,tileLengths);

        drawInventoryForPlayers();

        player1_lbl.setVisible(false);
        player2_lbl.setVisible(false);
        player3_lbl.setVisible(false);
        player4_lbl.setVisible(false);
        player5_lbl.setVisible(false);
        player6_lbl.setVisible(false);
        Player1Class_CB.setVisible(false);
        Player2Class_CB.setVisible(false);
        Player3Class_CB.setVisible(false);
        Player4Class_CB.setVisible(false);
        Player5Class_CB.setVisible(false);
        Player6Class_CB.setVisible(false);

        drawThreatLevel();

        for (int i = 0; i < NumOfPlayers; i++) {
            if(i==0)
            {
                drawPlayerClasses(g,i+1,Player1_Class);
            }
            if(i==1)
            {
                drawPlayerClasses(g,i+1,Player2_Class);
            }
            if(i==2)
            {
                drawPlayerClasses(g,i+1,Player3_Class);
            }
            if(i==3)
            {
                drawPlayerClasses(g,i+1,Player4_Class);
            }
            if(i==4)
            {
                drawPlayerClasses(g,i+1,Player5_Class);
            }
            if(i==5)
            {
                drawPlayerClasses(g,i+1,Player6_Class);
            }
        }
        drawActionPoints();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
