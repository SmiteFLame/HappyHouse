package com.ssafy.happyhouse.view;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.ssafy.environment.EnvironmentDao;
import com.ssafy.environment.EnvironmentInfo;
import com.ssafy.happyhouse.HappyHouseException;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HousePageBean;
import com.ssafy.happyhouse.model.service.HouseService;
import com.ssafy.happyhouse.model.service.HouseServiceImpl;


public class HouseInfoView{
	
	/**model들 */
	private HouseService 		houseService;
	private EnvironmentDao		envService;
	
	/** main 화면 */
	private JFrame frame;
	
	/** 주택 목록 화면*/
	// JFrame owner;
	

	/**창 닫기 버튼*/
	//private JButton  closeBt;
	
	/**주택 이미지 표시 Panel*/
	private JLabel	 			imgL;
	private JLabel[] 			houseInfoL;
	
	/**조회 조건*/
	private JCheckBox[]		  	chooseC;
	private JComboBox<String> 	findC; 
	private JTextField		  	wordTf;
	private JButton			  	searchBt;
	
	
	private JButton				mallButton;
	
	/**조회 내용 표시할 table*/
	private DefaultTableModel 	houseModel;
	private JTable			  	houseTable;
	private JScrollPane		  	housePan;
	private String[]		  	title = { "번호", "동", "아파트이름", "거래금액", "거래종류" };
	
	/**검색  조건*/
	private String	key;
	
	/**검색할 단어*/
	private String  word;
	private boolean[] searchType = { true, true, true, true };
	private String[] choice = { "all", "dong", "name" };
	private String[] imgs = {"건양하늘터.jpg", "경희궁의아침.jpg","광화문풍림스페이스본.jpg","교북동경희궁자이(4단지).jpg","다세대주택.jpg","동성아파트.jpg","무악동인완상아이파크.jpg"
			,"무악동현대.jpg","숭인동롯데캐슬천지인.jpg","신동아블루아광화문.jpg","아남1.jpg","창신동덕산.jpg","창신동두산.jpg","창신동창신쌍용1.jpg","창신동창신쌍용2.jpg","평동경희궁자이(3단지).jpg",
			"평창동갑을.jpg","현대뜨레비앙.jpg","홍파동경희궁자이(2단지).jpg"};
	
	/**화면에 표시하고 있는 주택*/
	private HouseDeal curHouse;

	
	private void showHouseInfo(int code) {
		
		curHouse = houseService.search(code);
		System.out.println(curHouse);
		
		//foodInfoL[0].setText(""+curfood.getCode());
		houseInfoL[0].setText("");
		houseInfoL[1].setText("");
		houseInfoL[2].setText(curHouse.getAptName());
		houseInfoL[3].setText(""+curHouse.getDealAmount());
		String rent = curHouse.getRentMoney();
		if(rent == null) {
			houseInfoL[4].setText("없음");
		}else {
			houseInfoL[4].setText(curHouse.getRentMoney());
		}
		houseInfoL[5].setText(""+curHouse.getBuildYear());
		houseInfoL[6].setText(curHouse.getArea()+"");
		houseInfoL[7].setText(String.format("%d년 %d월 %d일"
											,curHouse.getDealYear()
											,curHouse.getDealMonth()
											,curHouse.getDealDay()
											));
		houseInfoL[8].setText(curHouse.getDong());
		houseInfoL[9].setText(curHouse.getJibun());
		
		//System.out.println("###############" + curHouse.getImg());
		
		ImageIcon icon = null;
//		if( curHouse.getImg() != null && curHouse.getImg().trim().length() != 0) {
//			icon = new ImageIcon("img/" + curHouse.getImg());
//			System.out.println("#####" + icon.toString() + "####");
//		}else {
//			icon = new ImageIcon("img/다세대주택.jpg");
//		}
		
		
		if(curHouse.getAptName() != null && curHouse.getAptName().trim().length() != 0) {
			int i=0;
			for(; i<imgs.length; i++) {
				if(imgs[i].contains(curHouse.getAptName())) {
					icon = new ImageIcon("img/" + imgs[i]);
					System.out.println("#####" + icon.toString() + "####");
					break;
				}
			}
			if(i == imgs.length) {
				int ran = (int)(Math.random() * (imgs.length - 1)) + 1;
				icon = new ImageIcon("img/" + imgs[ran]);
				System.out.println("#####" + icon.toString() + "####");
			}
		}else {
			int ran = (int)(Math.random() * (imgs.length - 1)) + 1;
			icon = new ImageIcon("img/" + imgs[ran]);
			System.out.println("#####" + icon.toString() + "####");
		}

		imgL.setIcon(icon);
		  
		  

//		Image img = null;
//		try {
//			img = ImageIO.read(new File("img/"+curHouse.getImg()));
//         } catch (IOException ex) {
//        	 try {
//        		 img = ImageIO.read(new File("img/다세대주택.jpg"));
//			} catch (Exception e) {
//			}
//         }
//		img = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
//		imgL.setIcon(new ImageIcon(img));
	}
	
	public HouseInfoView(){
		/*Service들 생성 */
		houseService = new HouseServiceImpl();
		envService = new EnvironmentDao();
		
		/*메인 화면 설정 */
		frame = new JFrame("HappyHouse -- 아파트 거래 정보");
//		frame.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e){
//				frame.dispose();
//			}
//		});
		
		setMain();
		
		frame.setSize(1200, 800);
		frame.setResizable(true);
		frame.setVisible(true);
		showHouseInfo(1);
		//showHouses();
	}

	/**메인 화면인 주택 목록을 위한 화면 셋팅하는 메서드  */
	public void setMain(){
		
		/*왼쪽 화면을 위한 설정 */
		JPanel left = new JPanel(new BorderLayout());
		JPanel leftCenter = new JPanel(new GridLayout(1, 2));
		JPanel leftR = new JPanel(new GridLayout(10, 2));
		leftR.setBorder(BorderFactory.createEmptyBorder(0 , 10 , 10 , 10));
		
		String[] info= {"","","주택명","거래금액","월세금액","건축연도","전용면적","거래일","법정동","지번"};
		int size = info.length;
		JLabel infoL[] = new JLabel[size];
		houseInfoL = new JLabel[size];
		for (int i = 0; i <size; i++) {
			infoL[i] = new JLabel(info[i]);
			houseInfoL[i]=new JLabel("");
			leftR.add(infoL[i]);
			leftR.add(houseInfoL[i]);
		}
		imgL = new JLabel();
		leftCenter.add(imgL);
		leftCenter.add(leftR);

		mallButton = new JButton("상가 전체 보기");
		left.add(new JLabel("아파트 거래 정보", JLabel.CENTER),"North");
		left.add(leftCenter,"Center");
		left.add(mallButton,"South");
		
		
		/*오른쪽 화면을 위한 설정 */
		JPanel right = new JPanel(new BorderLayout());
		JPanel rightTop = new JPanel(new GridLayout(4, 2));
		JPanel rightTop1 = new JPanel(new GridLayout(1, 4));
		String[] chooseMeg= { "아파트 매매", "아파트 전월세", "주택 매매", "주택 전월세"};
		chooseC = new JCheckBox[chooseMeg.length];
		for (int i = 0, len= chooseMeg.length; i < len; i++) {
			chooseC[i] = new JCheckBox(chooseMeg[i], true);
			rightTop1.add(chooseC[i]);
		}
		
		
		JPanel rightTop2 = new JPanel(new GridLayout(1, 3));
		String[] item = {"---all---","동","아파트 이름"}; 
		findC = new JComboBox<String>(item);
		wordTf = new JTextField();
		searchBt = new JButton("검색");
		
		rightTop2.add(findC);
		rightTop2.add(wordTf);
		rightTop2.add(searchBt);
		
		rightTop.add(new Label(""));
		rightTop.add(rightTop1);
		rightTop.add(rightTop2);		
		rightTop.add(new Label(""));
		
		JPanel rightCenter = new JPanel(new BorderLayout());
		houseModel = new DefaultTableModel(title,20);
		houseTable = new JTable(houseModel);
		housePan = new JScrollPane(houseTable);
		houseTable.setColumnSelectionAllowed(true);
		rightCenter.add(new JLabel("거래 내역", JLabel.CENTER),"North");
		rightCenter.add(housePan,"Center");
		
		right.add(rightTop,"North");
		right.add(rightCenter,"Center");
		
		JPanel mainP = new JPanel(new GridLayout(1, 2));
		
		mainP.add(left);
		mainP.add(right);
		
		mainP.setBorder(BorderFactory.createEmptyBorder(20 , 10 , 10 , 10));
		frame.add(mainP,"Center");
		
		/*이벤트 연결*/

		houseTable.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int row =  houseTable.getSelectedRow();
				System.out.println("선택된 row : " + row);
				System.out.println("선택된 row의 column 값 :"+houseModel.getValueAt(row, 0));
				int code = Integer.parseInt(((String)houseModel.getValueAt(row, 0)).trim());
				showHouseInfo(code);
				
				String dong = ((String)houseModel.getValueAt(row, 1)).trim();
				searchEnv(dong);
			}
		});
		
		// complete code #01
		// 아래의 코드를 참조하여 아래 라인을 uncomment 하고 searchBt.addActionList() 를 Lambda 표현식으로 바꾸세요.
		 searchBt.addActionListener(new ActionListener() {
			 @Override
				public void actionPerformed(ActionEvent e) {
					searchHouses();
				}			 
		 });
		
		searchBt.addActionListener(e -> searchHouses());
		 
		mallButton.addActionListener(e -> new MallInfoView());
		
		showHouses();
	}
	
	/** 검색 조건에 맞는 환경 지도점검 내역 띄우기 */
	private void searchEnv(String dong) {
		List<EnvironmentInfo> envList = envService.searchAll(dong);
		for(EnvironmentInfo info : envList)
			System.out.println(envList);
		int size = envList.size();
		String[] header = {"업체명","점검기관명","지도점검구분","처분대상여부","점검사항","소재지주소"};
		String[][] data = new String[size][6];
		for(int i = 0; i < size; i++) {
			data[i][0] = envList.get(i).getFacName();
			data[i][1] = envList.get(i).getCheckArea();
			data[i][2] = envList.get(i).getRegularCheck();
			data[i][3] = envList.get(i).getDisposition();
			data[i][4] = envList.get(i).getCheckOption();
			data[i][5] = envList.get(i).getCheckArea();
		}
		
		JFrame newFrame = new JFrame("아파트 주변 환경 지도점검 내역");
		newFrame.setVisible(true);
		newFrame.setResizable(true);
		newFrame.setSize(700, 450);
		newFrame.setLocation(700,200);
		newFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				newFrame.setVisible(false);
				newFrame.dispose();
			}
		});

		JTable table = new JTable(data, header);
		JScrollPane scrollPane = new JScrollPane(table);
		newFrame.add(scrollPane);

	}
	
	
	/**검색 조건에 맞는 주택 정보 검색 */
	private void searchHouses() {
		for(int i = 0, size = chooseC.length; i<size; i++) {
			if(chooseC[i].isSelected()) {
				searchType[i] = true;
			}else {
				searchType[i] = false;
			}
		}
		word = wordTf.getText().trim();
		key = choice[findC.getSelectedIndex()];
		System.out.println("word:"+word+" key:"+key);
		showHouses();		
	}
	/**
	 * 주택 목록을  갱신하기 위한 메서드 
	 */
	public void showHouses(){
		HousePageBean  bean = new HousePageBean();
		bean.setSearchType(searchType);
		if(key !=null) {
			if(key.equals("dong")) {
				bean.setDong(word);
			}else if(key.equals("name")) {
				bean.setAptname(word);
			}
		}
		
		List<HouseDeal> deals = houseService.searchAll(bean);
		if(deals!=null){
			int i=0;
			String[][]data = new String[deals.size()][5];
			for (HouseDeal deal: deals) {
				data[i][0]= ""+deal.getNo();
				data[i][1]= deal.getDong();
				data[i][2]= deal.getAptName();
				data[i][3]= deal.getDealAmount();
				data[i++][4]= deal.getType();
			}
			houseModel.setDataVector(data, title);
		}
	}
//	public static void main(String[] args) {
//		new HouseInfoView();
//	}
	
}

