package edu.tacas21.gui;

public class Parameters {
	public int wlNum = 0;
	public int tkNum = 0;
	public int pcNum = 0;
	public int scNum = 0;
	public int chargerNum = 0;
	public int wlTravelingTimeLower = 0;
	public int wlTravelingTimeUpper = 0;
	public int wlTaskTimeLower = 0;
	public int wlTaskTimeUpper = 0;
	public int tkTravelingTimeLower = 0;
	public int tkTravelingTimeUpper = 0;
	public int tkTaskTimeLower = 0;
	public int tkTaskTimeUpper = 0;
	public int chTaskTimeLower = 0;
	public int chTaskTimeUpper = 0;
	public int goal = 0;
	public int iterations = 0;
	private int totalTime = 0; // unit: second
	private int taskNum = 3;
	public boolean save = true;
	public String path_save = "";
	public boolean compact = false;
	public String path_compact = "";

	/**
	 * 
	 * @param vt: unit: minute
	 */
	public void setTotalTime(int vt) {
		this.totalTime = vt * 60;
	}
	
	/**
	 * 
	 * @return unit: second
	 */
	public int getTotalTime() {
		return this.totalTime;
	}
	
	private int randomNumber(int lower, int upper) {
		int rand = (int) (Math.random() * (upper - lower + 1) + lower);

		return rand;
	}
	
	public int getAgentNum() {
		return this.wlNum + this.tkNum;
	}

	public int getDevNum() {
		int devNum = this.wlNum + this.pcNum + this.scNum + this.chargerNum;

		/*
		 * if (devNum <= this.wlNum) { devNum = this.wlNum; } if (devNum <= this.pcNum)
		 * { devNum = this.pcNum; } if (devNum <= this.scNum) { devNum = this.scNum; }
		 * if (devNum <= this.chargerNum) { devNum = this.chargerNum; }
		 */

		return devNum;
	}

	private String IDAgents() {
		String str = "/**ID of agents*/\n";

		for (int w = 0; w < this.wlNum; w++) {
			str += "const agentid_t ID_WHEELLOADER" + w + " = " + w + ";\n";
		}
		for (int t = 0; t < this.tkNum; t++) {
			str += "const agentid_t ID_TRUCK" + t + " = " + (this.wlNum + t) + ";\n";
		}

		return str + "\n";
	}

	private String IDDevices() {
		String str = "/**ID of devices*/\n";

		for (int p = 0; p < this.pcNum; p++) {
			str += "const deviceid_t ID_DV_PC" + p + " = " + p + ";\n";
		}
		for (int s = 0; s < this.scNum; s++) {
			str += "const deviceid_t ID_DV_SC" + s + " = " + (this.pcNum + s) + ";\n";
		}
		for (int c = 0; c < this.chargerNum; c++) {
			str += "const deviceid_t ID_DV_CH" + c + " = " + (this.pcNum + this.scNum + c) + ";\n";
		}

		return str + "\n";
	}

	private String IDMilestones() {
		String str = "/**ID of milestones*/\n";

		for (int w = 0; w < this.wlNum; w++) {
			str += "const milestoneid_t ID_STONE" + w + " = " + w + ";\n";
		}
		for (int p = 0; p < this.pcNum; p++) {
			str += "const milestoneid_t ID_PRIMARYCRUSHER" + p + " = " + (this.wlNum + p) + ";\n";
		}
		for (int s = 0; s < this.scNum; s++) {
			str += "const milestoneid_t ID_SECONDARYCRUSHER" + s + " = " + (this.wlNum + this.pcNum + s) + ";\n";
		}
		for (int c = 0; c < this.chargerNum; c++) {
			str += "const milestoneid_t ID_CHARGER" + c + " = " + (this.wlNum + this.pcNum + this.scNum + c) + ";\n";
		}

		return str + "\n";
	}

	private String IDTasks() {
		String str = "/**ID of tasks*/\n";

		str += "const taskid_t ID_DIG_WHEELLOADER = 0;\r\n" + "const taskid_t ID_UNLOAD_TO_TRUCKS_WHEELLOADER = 1;\r\n"
				+ "const taskid_t ID_LOAD_FROM_WHEELLOADERS_TRUCK = 0; // loading from wl or loading from primary crusher\r\n"
				+ "const taskid_t ID_LOAD_FROM_PRIMARY_TRUCK = 0; // truck only needs to choose one of them to execute, so the tasks' ID are the same\r\n"
				+ "const taskid_t ID_UNLOAD_TO_SECONDARY_TRUCK = 1;\r\n"
				+ "const taskid_t ID_TASK_CHARGE = 2; // charge task for wl and tk";

		return str + "\n";
	}

	private String IDEvents() {
		String str = "/**ID of events*/\n";

		str += "const eventid_t ID_EVENT_BATTERY_LOW = 0;";

		return str + "\n";
	}

	private String defDevices() {
		String str = "/**devices*/\n";

		for (int w = 0; w < this.wlNum; w++) {
			str += "const device_t DV_STONE" + w + "_NULL = {NULLDEVICE, ID_STONE" + w + "};\n";
		}
		for (int p = 0; p < this.pcNum; p++) {
			str += "const device_t DV_PC" + p + " = {ID_DV_PC" + p + ", ID_PRIMARYCRUSHER" + p + "};\n";
		}
		for (int s = 0; s < this.scNum; s++) {
			str += "const device_t DV_SC" + s + " = {ID_DV_SC" + s + ", ID_SECONDARYCRUSHER" + s + "};\n";
		}
		for (int c = 0; c < this.chargerNum; c++) {
			str += "const device_t DV_CH" + c + " = {ID_DV_CH" + c + ", ID_CHARGER" + c + "};\n";
		}

		return str + "\n";
	}

	private String defTasks() {
		int devNum = this.getDevNum();
		String str = "/**tasks and their milestones*/\n";

		str += "const task_t TASK_DIG = {ID_DIG_WHEELLOADER, UNKOWNDEVICE, {";
		for (int w = 0; w < this.wlNum; w++) {
			if (w != this.wlNum - 1) {
				str += "DV_STONE" + w + "_NULL, ";
			} else {
				str += "DV_STONE" + w + "_NULL";
			}
		}
		for (int r = 0; r < devNum - this.wlNum; r++) {
			str += ", UNKOWNDEVICE";
		}
		str += "}};\n";

		str += "const task_t TASK_UNLOAD_TO_TRUCKS = {ID_UNLOAD_TO_TRUCKS_WHEELLOADER, UNKOWNDEVICE, {";
		for (int w = 0; w < this.wlNum; w++) {
			if (w != this.wlNum - 1) {
				str += "DV_STONE" + w + "_NULL, ";
			} else {
				str += "DV_STONE" + w + "_NULL";
			}
		}
		for (int r = 0; r < devNum - this.wlNum; r++) {
			str += ", UNKOWNDEVICE";
		}
		str += "}};\n";

		str += "const task_t TASK_LOAD_FROM_WHEELLOADERS = {ID_LOAD_FROM_WHEELLOADERS_TRUCK, UNKOWNDEVICE, {";
		for (int w = 0; w < this.wlNum; w++) {
			if (w != this.wlNum - 1) {
				str += "DV_STONE" + w + "_NULL, ";
			} else {
				str += "DV_STONE" + w + "_NULL";
			}
		}
		for (int r = 0; r < devNum - this.wlNum; r++) {
			str += ", UNKOWNDEVICE";
		}
		str += "}};\n";

		str += "const task_t TASK_LOAD_FROM_PRIMARY = {ID_LOAD_FROM_PRIMARY_TRUCK, UNKOWNDEVICE, {";
		for (int p = 0; p < this.pcNum; p++) {
			if (p != this.pcNum - 1) {
				str += "DV_PC" + p + ", ";
			} else {
				str += "DV_PC" + p;
			}
		}
		for (int r = 0; r < devNum - this.pcNum; r++) {
			str += ", UNKOWNDEVICE";
		}
		str += "}};\n";

		str += "const task_t TASK_UNLOAD_TO_SECONDARY = {ID_UNLOAD_TO_SECONDARY_TRUCK, UNKOWNDEVICE, {";
		for (int s = 0; s < this.scNum; s++) {
			if (s != this.scNum - 1) {
				str += "DV_SC" + s + ", ";
			} else {
				str += "DV_SC" + s;
			}
		}
		for (int r = 0; r < devNum - this.scNum; r++) {
			str += ", UNKOWNDEVICE";
		}
		str += "}};\n";

		if (this.chargerNum != 0) {
			str += "const task_t TASK_CHARGE = {ID_TASK_CHARGE, UNKOWNDEVICE, {";
			for (int c = 0; c < this.chargerNum; c++) {
				if (c != this.chargerNum - 1) {
					str += "DV_CH" + c + ", ";
				} else {
					str += "DV_CH" + c;
				}
			}
			for (int r = 0; r < devNum - this.chargerNum; r++) {
				str += ", UNKOWNDEVICE";
			}
			str += "}};\n";
		}

		return str + "\n";
	}

	private String defPreconditions() {
		String str = "/**preconditions of tasks*/\n";

		for (int w = 0; w < this.wlNum; w++) {
			str += "const precondition_t PRE_UNLOAD_TO_TRUCK_WL" + w + " = {ID_WHEELLOADER" + w
					+ ", ID_DIG_WHEELLOADER};\n";
		}
		for (int t = 0; t < this.tkNum; t++) {
			str += "const precondition_t PRE_LOAD_FROM_WHEELLOADER_TRUCK" + t + " = {ID_TRUCK" + t
					+ ", ID_LOAD_FROM_PRIMARY_TRUCK};\n";
			str += "const precondition_t PRE_LOAD_FROM_PRIMARY_TRUCK" + t + " = {ID_TRUCK" + t
					+ ", ID_LOAD_FROM_WHEELLOADERS_TRUCK};\n";
			str += "const precondition_t PRE_UNLOAD_TO_SECONDARY_TRUCK" + t + "[2] = {{ID_TRUCK" + t
					+ ", ID_LOAD_FROM_WHEELLOADERS_TRUCK}, " + "{ID_TRUCK" + t + ", ID_LOAD_FROM_PRIMARY_TRUCK}};\n";
		}

		return str + "\n";
	}

	private String defCollaborations() {
		String str = "/**collaboration between agents*/\n";

		for (int w = 0; w < this.wlNum; w++) {
			str += "const collaboration_t COL_UNLOAD_TO_TRUCKS_WL" + w + "[" + this.tkNum + "] = {";
			for (int t = 0; t < this.tkNum; t++) {
				str += "{ID_TRUCK" + t + ", ID_STONE" + w;
				if (t != this.tkNum - 1) {
					str += "}, ";
				}
			}
			str += "}};\n";
		}

		str += "const collaboration_t COL_LOAD_FROM_WHEELLOADERS[" + this.wlNum + "] = {";
		for (int w = 0; w < this.wlNum; w++) {
			str += "{ID_WHEELLOADER" + w + ", ID_STONE" + w;
			if (w != this.wlNum - 1) {
				str += "}, ";
			}
		}
		str += "}};\n";

		return str + "\n";
	}

	private String defGoalIte() {
		String str = "/**parameters of the mission*/\n";

		str += "const goalrange_t GOAL = " + this.goal + ";\n";
		str += "const goalrange_t ITERATIONS = " + this.iterations + ";\n";

		return str + "\n";
	}

	private String defInitAgent() {
		int randDevice = 0, randPosition = 0;
		String str = "/**initial information of agents*/\n";
		String finalStr = "const agent_t INIT_AGENTS[NOAGENTS] = {";

		for (int w = 0; w < this.wlNum; w++) {
			str += "const agent_t INIT_WHEELLOADER" + w + " = " + "{ID_STONE" + w + ", TASK_IDLE, " + "{";
			for (int t = 0; t < this.taskNum; t++) {
				str += "UNSTARTED";
				if (t != this.taskNum - 1) {
					str += ", ";
				}
			}
			str += "}, {SLEEP}};\n";

			finalStr += "INIT_WHEELLOADER" + w + ",";
		}

		for (int tk = 0; tk < this.tkNum; tk++) {
			randDevice = this.randomNumber(0, 2); // stone, or primary crusher, or secondary crusher
			if (randDevice == 0) {
				randPosition = this.randomNumber(0, this.wlNum - 1);
				str += "const agent_t INIT_TRUCK" + tk + " = " + "{ID_STONE" + randPosition + ", TASK_IDLE, " + "{";
			}
			if (randDevice == 1) {
				randPosition = this.randomNumber(0, this.pcNum - 1);
				str += "const agent_t INIT_TRUCK" + tk + " = " + "{ID_PRIMARYCRUSHER" + randPosition + ", TASK_IDLE, "
						+ "{";
			}
			if (randDevice == 2) {
				randPosition = this.randomNumber(0, this.scNum - 1);
				str += "const agent_t INIT_TRUCK" + tk + " = " + "{ID_SECONDARYCRUSHER" + randPosition + ", TASK_IDLE, "
						+ "{";
			}
			for (int t = 0; t < this.taskNum; t++) {
				str += "UNSTARTED";
				if (t != this.taskNum - 1) {
					str += ", ";
				}
			}
			str += "}, {SLEEP}};\n";

			finalStr += "INIT_TRUCK" + tk;
			if (tk != this.tkNum - 1) {
				finalStr += ",";
			}
		}
		finalStr += "};\n";

		return str + finalStr + "\n";
	}

	private String defTA() {
		int rand = 0, bcet = 0, wcet = 0;
		String str = "/** Agents */\nRong = Referee(INIT_AGENTS, GOAL);\n";
		String strDelaration = "system Rong,\n";

		str += "/** Movement */\n";
		strDelaration += "/**map*/\n";
		for (int w = 0; w < this.wlNum; w++) {
			str += "//wheel loder " + w + "\n";
			for (int c = 0; c < this.chargerNum; c++) {
				rand = this.randomNumber(this.wlTravelingTimeLower, this.wlTravelingTimeUpper);
				str += "m_stone" + w + "Charger" + c + "_WL" + w + " = Movement(ID_WHEELLOADER" + w + ", ID_STONE" + w
						+ ", ID_CHARGER" + c + ", " + rand + ", TASK_UNLOAD_TO_TRUCKS, TASK_CHARGE);\n";
				str += "m_charger" + c + "Stone" + w + "_WL" + w + " = Movement(ID_WHEELLOADER" + w + ", ID_CHARGER" + c
						+ ", ID_STONE" + w + ", " + rand + ", TASK_CHARGE, TASK_UNLOAD_TO_TRUCKS);\n";
				strDelaration += "m_stone" + w + "Charger" + c + "_WL" + w + ",";
				strDelaration += "m_charger" + c + "Stone" + w + "_WL" + w + ",";
			}
		}
		strDelaration += "\n";

		for (int tk = 0; tk < this.tkNum; tk++) {
			str += "//wheel loder " + tk + "\n";
			// stones to secondary crushers and chargers
			for (int st = 0; st < this.wlNum; st++) {
				for (int sc = 0; sc < this.scNum; sc++) {
					rand = this.randomNumber(this.tkTravelingTimeLower, this.tkTravelingTimeUpper);
					str += "m_stone" + st + "Secondary" + sc + "_TK" + tk + " = Movement(ID_TRUCK" + tk + ", ID_STONE"
							+ st + ", ID_SECONDARYCRUSHER" + sc + ", " + rand
							+ ", TASK_LOAD_FROM_WHEELLOADERS, TASK_UNLOAD_TO_SECONDARY);\n";
					str += "m_secondary" + sc + "Stone" + st + "_TK" + tk + " = Movement(ID_TRUCK" + tk
							+ ", ID_SECONDARYCRUSHER" + sc + ", ID_STONE" + st + ", " + rand
							+ ", TASK_UNLOAD_TO_SECONDARY, TASK_LOAD_FROM_WHEELLOADERS);\n";
					strDelaration += "m_stone" + st + "Secondary" + sc + "_TK" + tk + ",";
					strDelaration += "m_secondary" + sc + "Stone" + st + "_TK" + tk + ",";
				}
				strDelaration += "\n";
				for (int ch = 0; ch < this.chargerNum; ch++) {
					rand = this.randomNumber(this.tkTravelingTimeLower, this.tkTravelingTimeUpper);
					str += "m_stone" + st + "Charger" + ch + "_TK" + tk + " = Movement(ID_TRUCK" + tk + ", ID_STONE"
							+ st + ", ID_CHARGER" + ch + ", " + rand + ", TASK_LOAD_FROM_WHEELLOADERS, TASK_CHARGE);\n";
					str += "m_charger" + ch + "Stone" + st + "_TK" + tk + " = Movement(ID_TRUCK" + tk + ", ID_CHARGER"
							+ ch + ", ID_STONE" + st + ", " + rand + ", TASK_CHARGE, TASK_LOAD_FROM_WHEELLOADERS);\n";
					strDelaration += "m_stone" + st + "Charger" + ch + "_TK" + tk + ",";
					strDelaration += "m_charger" + ch + "Stone" + st + "_TK" + tk + ",";
				}
				strDelaration += "\n";
			}
			// primary crushers to secondary crushers and chargers
			for (int pc = 0; pc < this.pcNum; pc++) {
				for (int sc = 0; sc < this.scNum; sc++) {
					rand = this.randomNumber(this.tkTravelingTimeLower, this.tkTravelingTimeUpper);
					str += "m_primary" + pc + "Secondary" + sc + "_TK" + tk + " = Movement(ID_TRUCK" + tk
							+ ", ID_PRIMARYCRUSHER" + pc + ", ID_SECONDARYCRUSHER" + sc + ", " + rand
							+ ", TASK_LOAD_FROM_PRIMARY, TASK_UNLOAD_TO_SECONDARY);\n";
					str += "m_secondary" + sc + "Primary" + pc + "_TK" + tk + " = Movement(ID_TRUCK" + tk
							+ ", ID_SECONDARYCRUSHER" + sc + ", ID_PRIMARYCRUSHER" + pc + ", " + rand
							+ ", TASK_UNLOAD_TO_SECONDARY, TASK_LOAD_FROM_PRIMARY);\n";
					strDelaration += "m_primary" + pc + "Secondary" + sc + "_TK" + tk + ",";
					strDelaration += "m_secondary" + sc + "Primary" + pc + "_TK" + tk + ",";
				}
				strDelaration += "\n";
				for (int ch = 0; ch < this.chargerNum; ch++) {
					rand = this.randomNumber(this.tkTravelingTimeLower, this.tkTravelingTimeUpper);
					str += "m_primary" + pc + "Charger" + ch + "_TK" + tk + " = Movement(ID_TRUCK" + tk
							+ ", ID_PRIMARYCRUSHER" + pc + ", ID_CHARGER" + ch + ", " + rand
							+ ",TASK_LOAD_FROM_PRIMARY, TASK_CHARGE);\n";
					str += "m_charger" + ch + "Primary" + pc + "_TK" + tk + " = Movement(ID_TRUCK" + tk + ", ID_CHARGER"
							+ ch + ", ID_PRIMARYCRUSHER" + pc + ", " + rand
							+ ", TASK_CHARGE, TASK_LOAD_FROM_PRIMARY);\n";
					strDelaration += "m_primary" + pc + "Charger" + ch + "_TK" + tk + ",";
					strDelaration += "m_charger" + ch + "Primary" + pc + "_TK" + tk + ",";
				}
				strDelaration += "\n";
			}
		}

		str += "/** Tasks */\n";
		strDelaration += "/**tasks*/\n";
		for (int w = 0; w < this.wlNum; w++) {
			str += "//wheel loder " + w + "\n";
			bcet = this.randomNumber(this.wlTaskTimeLower, this.wlTaskTimeUpper);
			wcet = this.randomNumber(bcet, this.wlTaskTimeUpper);
			str += "t_digging_WL" + w + " = " + "TaskNoCondition(ID_WHEELLOADER" + w + ", " + bcet + ", " + wcet
					+ ", TASK_DIG, false);\n";
			strDelaration += "t_digging_WL" + w + ",";

			bcet = this.randomNumber(this.wlTaskTimeLower, this.wlTaskTimeUpper);
			wcet = this.randomNumber(bcet, this.wlTaskTimeUpper);
			str += "t_unloading_WL" + w + " = " + "Collaboration_I(ID_WHEELLOADER" + w + ", " + bcet + ", " + wcet
					+ ", TASK_UNLOAD_TO_TRUCKS, PRE_UNLOAD_TO_TRUCK_WL" + w + ", COL_UNLOAD_TO_TRUCKS_WL" + w
					+ ", true);\n";
			strDelaration += "t_unloading_WL" + w + ",";

			if (this.chargerNum != 0) {
				bcet = this.randomNumber(this.wlTaskTimeLower, this.wlTaskTimeUpper);
				wcet = this.randomNumber(bcet, this.wlTaskTimeUpper);
				str += "t_charging_WL" + w + " = " + "TaskEvent(ID_WHEELLOADER" + w + ", ID_EVENT_BATTERY_LOW, " + bcet
						+ ", " + wcet + ", TASK_CHARGE);\n";
				strDelaration += "t_charging_WL" + w + ",";
			}
		}
		strDelaration += "\n";

		for (int tk = 0; tk < this.tkNum; tk++) {
			str += "//truck " + tk + "\n";
			bcet = this.randomNumber(this.tkTaskTimeLower, this.tkTaskTimeUpper);
			wcet = this.randomNumber(bcet, this.tkTaskTimeUpper);
			str += "t_loadingFromWL_TK" + tk + " = " + "Collaboration_H(ID_TRUCK" + tk
					+ ", TASK_LOAD_FROM_WHEELLOADERS, PRE_LOAD_FROM_WHEELLOADER_TRUCK" + tk
					+ ", COL_LOAD_FROM_WHEELLOADERS, false);\n";
			strDelaration += "t_loadingFromWL_TK" + tk + ",";

			bcet = this.randomNumber(this.tkTaskTimeLower, this.tkTaskTimeUpper);
			wcet = this.randomNumber(bcet, this.tkTaskTimeUpper);
			str += "t_loadingFromPrimary_TK" + tk + " = " + "TaskWithCondition(ID_TRUCK" + tk + ", " + bcet + ", "
					+ wcet + ", TASK_LOAD_FROM_PRIMARY, PRE_LOAD_FROM_PRIMARY_TRUCK" + tk + ", false);\n";
			strDelaration += "t_loadingFromPrimary_TK" + tk + ",";

			bcet = this.randomNumber(this.tkTaskTimeLower, this.tkTaskTimeUpper);
			wcet = this.randomNumber(bcet, this.tkTaskTimeUpper);
			str += "t_unloadingToSecond_TK" + tk + " = " + "TaskWithConditions(ID_TRUCK" + tk + ", " + bcet + ", "
					+ wcet + ", TASK_UNLOAD_TO_SECONDARY, PRE_UNLOAD_TO_SECONDARY_TRUCK" + tk
					+ ", GOAL/ITERATIONS, true);\n";

			if (this.chargerNum != 0) {
				strDelaration += "t_unloadingToSecond_TK" + tk + ",";
				bcet = this.randomNumber(this.tkTaskTimeLower, this.tkTaskTimeUpper);
				wcet = this.randomNumber(bcet, this.tkTaskTimeUpper);
				str += "t_charging_TK" + tk + " = " + "TaskEvent(ID_TRUCK" + tk + ", ID_EVENT_BATTERY_LOW, " + bcet
						+ ", " + wcet + ", TASK_CHARGE);\n";
				strDelaration += "t_charging_TK" + tk + ",";
			} else {
				if (tk != this.tkNum - 1) {
					strDelaration += "t_unloadingToSecond_TK" + tk + ",";
				} else {
					strDelaration += "t_unloadingToSecond_TK" + tk + ";";
				}
			}
		}
		strDelaration += "\n";

		if (this.chargerNum != 0) {
			bcet = this.randomNumber(this.chTaskTimeLower, this.chTaskTimeUpper);
			wcet = this.randomNumber(bcet, this.chTaskTimeUpper);
			for (int w = 0; w < this.wlNum; w++) {
				str += "o_battery_WL" + w + " = Monitor(ID_WHEELLOADER" + w + ", ID_EVENT_BATTERY_LOW, " + bcet + ", "
						+ wcet + ");\n";
				strDelaration += "o_battery_WL" + w + ",";
			}
			bcet = this.randomNumber(this.chTaskTimeLower, this.chTaskTimeUpper);
			wcet = this.randomNumber(bcet, this.chTaskTimeUpper);
			for (int tk = 0; tk < this.tkNum; tk++) {
				str += "o_battery_TK" + tk + " = Monitor(ID_TRUCK" + tk + ", ID_EVENT_BATTERY_LOW, " + bcet + ", "
						+ wcet + ");\n";
				if (tk != this.tkNum - 1) {
					strDelaration += "o_battery_TK" + tk + ",";
				} else {
					strDelaration += "o_battery_TK" + tk + ";\n";
				}
			}
		}

		return str + "\n" + strDelaration + "\n";
	}

	public String getDelarations() {
		return this.IDAgents() + this.IDDevices() + this.IDMilestones() + this.IDTasks() + this.IDEvents()
				+ this.defDevices() + this.defTasks() + this.defPreconditions() + this.defCollaborations()
				+ this.defGoalIte() + this.defInitAgent() + this.defTA();
	}

	public String getLearningQuery() {
		String str = "strategy policy = maxE(var - timeConsumption)[<=TOTALTIME]{\r\n" + "Rong.location,\n";

		for (int w = 0; w < this.wlNum; w++) {
			for (int c = 0; c < this.chargerNum; c++) {
				str += "m_stone" + w + "Charger" + c + "_WL" + w + ".location, ";
				str += "m_charger" + c + "Stone" + w + "_WL" + w + ".location, ";
			}
			str += "\n";
		}

		for (int tk = 0; tk < this.tkNum; tk++) {
			// stones to secondary crushers and chargers
			for (int st = 0; st < this.wlNum; st++) {
				for (int sc = 0; sc < this.scNum; sc++) {
					str += "m_stone" + st + "Secondary" + sc + "_TK" + tk + ".location, ";
					str += "m_secondary" + sc + "Stone" + st + "_TK" + tk + ".location, ";
				}
				str += "\n";
				for (int ch = 0; ch < this.chargerNum; ch++) {
					str += "m_stone" + st + "Charger" + ch + "_TK" + tk + ".location, ";
					str += "m_charger" + ch + "Stone" + st + "_TK" + tk + ".location, ";
				}
				str += "\n";
			}
			// primary crushers to secondary crushers and chargers
			for (int pc = 0; pc < this.pcNum; pc++) {
				for (int sc = 0; sc < this.scNum; sc++) {
					str += "m_primary" + pc + "Secondary" + sc + "_TK" + tk + ".location, ";
					str += "m_secondary" + sc + "Primary" + pc + "_TK" + tk + ".location, ";
				}
				str += "\n";
				for (int ch = 0; ch < this.chargerNum; ch++) {
					str += "m_primary" + pc + "Charger" + ch + "_TK" + tk + ".location, ";
					str += "m_charger" + ch + "Primary" + pc + "_TK" + tk + ".location, ";
				}
				str += "\n";
			}
		}

		for (int w = 0; w < this.wlNum; w++) {
			str += "t_digging_WL" + w + ".location, ";
			str += "t_unloading_WL" + w + ".location, ";

			if (this.chargerNum != 0) {
				str += "t_charging_WL" + w + ".location, ";
			}
			str += "\n";
		}

		for (int tk = 0; tk < this.tkNum; tk++) {
			str += "t_loadingFromWL_TK" + tk + ".location, ";
			str += "t_loadingFromPrimary_TK" + tk + ".location, ";

			if (this.chargerNum != 0) {
				str += "t_unloadingToSecond_TK" + tk + ".location, ";
				str += "t_charging_TK" + tk + ".location,";
			} else {
				str += "t_unloadingToSecond_TK" + tk + ".location, ";
			}
			str += "\n";
		}

		if (this.chargerNum != 0) {
			for (int w = 0; w < this.wlNum; w++) {
				str += "o_battery_WL" + w + ".location, ";
			}
			for (int tk = 0; tk < this.tkNum; tk++) {
				str += "o_battery_TK" + tk + ".location, ";
			}
			str += "\n";
		}

		str += "var,\n";

		String agent = "";
		for (int a = 0; a < this.wlNum + this.tkNum; a++) {
			agent = "agents[" + a + "]";
			str += agent + ".a_position, " + agent + ".a_monitors[0], " + agent + ".a_task.t_id, " + agent
					+ ".a_task.t_deviceUse.d_id, " + agent + ".a_task.t_deviceUse.d_position, " + agent
					+ ".a_status[ID_DIG_WHEELLOADER], " + agent + ".a_status[ID_UNLOAD_TO_TRUCKS_WHEELLOADER]";
			if(a != this.wlNum + this.tkNum - 1) {
				str += ",\n";
			}
			else {
				str += "\n";
			}
		}

		str += "}->{}:<> Rong.Win || Rong.Lose\n";

		return str;
	}
	
	public String getReachabilityQuery() {
		String str = "E<> isGameWon() under policy";
		
		return str;
	}
	
	public String getLivenessQuery() {
		String str = "A<> isGameWon() under policy";
		
		return str;
	}
	
	public String getSaveStrategyQuery(String path) {
		String str = "saveStrategy(\"" + path + "\", policy)";
		
		return str;
	}

	@Override
	public String toString() {
		/*
		 * String str1 = "wlNum: " + this.wlNum + "\ntkNum: " + this.tkNum + "\npcNum: "
		 * + this.pcNum + "\nscNum: " + this.scNum + "\nchargerNum: " + this.chargerNum
		 * + "\n"; String str2 = "";
		 * 
		 * for (int t = 0; t <= this.tkNum; t++) { for (int w = 0; w <= this.wlNum; w++)
		 * { str2 += "tk" + t + " and wl" + w + ": " +
		 * this.randomNumber(this.tkTravelingTimeLower, this.tkTravelingTimeUpper) +
		 * "\n"; } }
		 * 
		 * for (int t = 0; t <= this.tkNum; t++) { for (int pc = 0; pc <= this.pcNum;
		 * pc++) { str2 += "tk" + t + " and pc" + pc + ": " +
		 * this.randomNumber(this.tkTravelingTimeLower, this.tkTravelingTimeUpper) +
		 * "\n"; } }
		 * 
		 * for (int t = 0; t <= this.tkNum; t++) { for (int sc = 0; sc <= this.scNum;
		 * sc++) { str2 += "tk" + t + " and sc" + sc + ": " +
		 * this.randomNumber(this.tkTravelingTimeLower, this.tkTravelingTimeUpper) +
		 * "\n"; } }
		 * 
		 * for (int t = 0; t <= this.tkNum; t++) { for (int c = 0; c <= this.chargerNum;
		 * c++) { str2 += "tk" + t + " and charger" + c + ": " +
		 * this.randomNumber(this.tkTravelingTimeLower, this.tkTravelingTimeUpper) +
		 * "\n"; } }
		 * 
		 * for (int w = 0; w <= this.wlNum; w++) { for (int c = 0; c <= this.chargerNum;
		 * c++) { str2 += "wl" + w + " and charger" + c + ": " +
		 * this.randomNumber(this.wlTravelingTimeLower, this.wlTravelingTimeUpper) +
		 * "\n"; } }
		 * 
		 * for (int t = 0; t <= this.tkNum; t++) { str2 += "tk" + t + " load wl time: "
		 * + this.randomNumber(this.tkTaskTimeLower, this.tkTaskTimeUpper) + "\n"; str2
		 * += "tk" + t + " load pc time: " + this.randomNumber(this.tkTaskTimeLower,
		 * this.tkTaskTimeUpper) + "\n"; str2 += "tk" + t + " unload sc time: " +
		 * this.randomNumber(this.tkTaskTimeLower, this.tkTaskTimeUpper) + "\n"; for
		 * (int c = 0; c <= this.chargerNum; c++) { str2 += "tk" + t +
		 * " charge at charger" + c + "time: " + this.randomNumber(this.tkTaskTimeLower,
		 * this.tkTaskTimeUpper) + "\n"; } }
		 * 
		 * for (int w = 0; w <= this.wlNum; w++) { for (int c = 0; c <= this.chargerNum;
		 * c++) { str2 += "wl" + w + " charge at charger" + c + "time: " +
		 * this.randomNumber(this.wlTaskTimeLower, this.wlTaskTimeUpper) + "\n"; } }
		 * 
		 * return str1 + str2 + "\n";
		 */
		return "";
	}

	public Parameters random() {
		Parameters setting = new Parameters();

		setting.save = this.save;
		setting.path_save = this.path_save;
		setting.compact = this.compact;
		setting.path_compact = this.path_compact;
		setting.totalTime = this.totalTime;
		setting.goal = this.goal;
		setting.iterations = this.iterations;
		setting.wlNum = this.randomNumber(1, this.wlNum);
		setting.tkNum = this.randomNumber(1, this.tkNum);
		setting.pcNum = this.randomNumber(1, this.pcNum);
		setting.scNum = this.randomNumber(1, this.scNum);
		setting.chargerNum = this.randomNumber(0, this.chargerNum);
		setting.wlTravelingTimeLower = this.wlTravelingTimeLower;
		setting.wlTravelingTimeUpper = this.wlTravelingTimeUpper;
		setting.wlTaskTimeLower = this.wlTaskTimeLower;
		setting.wlTaskTimeUpper = this.wlTaskTimeUpper;
		setting.tkTravelingTimeLower = this.tkTravelingTimeLower;
		setting.tkTravelingTimeUpper = this.tkTravelingTimeUpper;
		setting.tkTaskTimeLower = this.tkTaskTimeLower;
		setting.tkTaskTimeUpper = this.tkTaskTimeUpper;

		return setting;
	}
}
