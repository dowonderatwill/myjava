
package sysdesign;

import java.util.*;
import java.util.stream.Collectors;

class DCLoadBalancer {
    HashMap<Integer,Integer> activeMachines;
    HashMap<Integer,Integer> appToLoadUse;
    HashMap<Integer,List<Integer>> machinesToApps;
    PriorityQueue<Integer> q; // prioritized machines
    HashMap<Integer,Integer> appToMachnies; // app to machines;
    
   
	public DCLoadBalancer() {
        activeMachines 	= new HashMap<>();
        appToLoadUse 	= new HashMap<>();
        machinesToApps 	= new HashMap<>();
        q				= new PriorityQueue<>((a,b)->{
        		int loadA = activeMachines.get(a);
        		int loadB = activeMachines.get(b);
        		if(loadB == loadA) return Integer.compare(a, b);
        		else return Integer.compare(loadB, loadA);
        });
        appToMachnies 	= new HashMap<>();
    }
    
    public void addMachine(int machineId, int capacity) {
    	activeMachines.put(machineId, capacity);
    	q.add(machineId);
        
    }
    
    public void removeMachine(int machineId) {
    	
    	q.remove(machineId);
    	activeMachines.remove(machineId);
    	
    	List<Integer> listApps = machinesToApps.get(machineId);
    	machinesToApps.remove(machineId);
    	
    	if(null== listApps || 0==listApps.size()) return;
    	
    	for(int app : listApps) {
    		addApplication(app, appToLoadUse.get(app));
    	}
        
    }
    
    public int addApplication(int appId, int loadUse) {
    	
    	Integer pm = q.poll();if(null==pm) return -1;
    	Integer cc = activeMachines.get(pm);
    	
    	if(loadUse> cc) return -1;
    	cc-=loadUse;
    	
    	activeMachines.put(pm, cc);
    	
    	List<Integer> listApps = machinesToApps.getOrDefault(pm, new ArrayList<>());
    	
    	listApps.add(appId);
    	machinesToApps.put(pm, listApps);
    	
    	appToLoadUse.put(appId, loadUse);
    	
    	q.add(pm);
    	
    	appToMachnies.put(appId, pm);
    	
    	return pm;
        
    }
    
    public void stopApplication(int appId) {
    	Integer machine = appToMachnies.get(appId);
    	Integer load 	= appToLoadUse.get(appId);
    	if(null==machine) return;
    	
    	Integer mc = activeMachines.get(machine);
    	mc += load;
    	q.remove(machine);
    	q.add(machine);
    	appToMachnies.remove(appId);
    	
		List<Integer> listApps = machinesToApps.get(machine);
		if (listApps.contains(appId)) {
			
			Iterator<Integer> it = listApps.iterator();
			while(it.hasNext()) {
				Integer ap = it.next();
				if(ap.equals(appId))
					it.remove();
			}
			
		}
		
		if(listApps.size()>0)
			machinesToApps.put(machine, listApps);
    	
        
    }
    
    public List<Integer> getApplications(int machineId) {
    	List<Integer> list = machinesToApps.get(machineId);
    	if(null==list) 
    		return new ArrayList<Integer>();
    	else
    		return list.stream().limit(10).collect(Collectors.toList());
    }
    
    /*
     ["DCLoadBalancer","addMachine","addMachine","addMachine","addMachine","addApplication","addApplication","addApplication","addApplication","getApplications","addMachine","addApplication","stopApplication","addApplication","getApplications","removeMachine","getApplications"]
[[],[1,1],[2,10],[3,10],[4,15],[1,3],[2,11],[3,6],[4,5],[2],[5,10],[5,5],[3],[6,5],[4],[4],[2]]
     */
     public static void main(String[] args) {
    	 
    			  
		/*
    	 String[] inst = {"addMachine","addMachine","addMachine", "addMachine",
    			 "addApplication","addApplication","addApplication","addApplication",
    			 "getApplications",
    			 "addMachine","addApplication","stopApplication","addApplication","getApplications","removeMachine","getApplications"};
    	 int[][] params ={ {1,1},{2,10},{3,10},{4,15},
    			 {1,3},{2,11},{3,6},{4,5},
    			 {2},
    			 {5,10},{5,5},{3},{6,5},{4},{4},{2}};
    	 */
    	 String[] inst = {"addMachine","getApplications","addMachine","getApplications","getApplications","removeMachine","getApplications","addMachine","getApplications","getApplications","addMachine","removeMachine","addApplication","addMachine","removeMachine","getApplications","getApplications","addApplication","addMachine","addMachine","getApplications","getApplications","getApplications","addApplication","removeMachine","removeMachine","removeMachine","addApplication","getApplications","removeMachine","addMachine","addApplication","getApplications","removeMachine","addMachine","getApplications","getApplications","addMachine","removeMachine","addMachine","addApplication","getApplications","getApplications","getApplications","getApplications","getApplications","getApplications","getApplications","getApplications","removeMachine","getApplications","addApplication","getApplications","getApplications","addApplication","addMachine","addApplication","getApplications","getApplications"};
    	 int[][] params = {{12264,47135},{12264},{23997,34056},{12264},{12264},{12264},{23997},{98265,54003},{23997},{98265},{54533,71366},{98265},{78014,8255},{43462,85258},{54533},{23997},{43462},{92855,18342},{87711,47893},{15946,54614},{43462},{87711},{87711},{81574,91970},{23997},{87711},{15946},{95319,30396},{43462},{43462},{37053,57590},{42444,41923},{37053},{37053},{99424,56702},{99424},{99424},{23317,9135},{99424},{55754,74790},{96466,15626},{55754},{23317},{23317},{23317},{55754},{23317},{55754},{55754},{55754},{23317},{84463,77320},{23317},{23317},{21655,29544},{49468,56127},{50191,1576},{23317},{23317}};
		 //*/
    	 DCLoadBalancer dc = new DCLoadBalancer();
    			 
    	 int i=0;
    	 for(String s : inst) {
    		String res="null";
    		 switch (s) {
    			 case "addMachine":
    				 dc.addMachine(params[i][0], params[i][1]);
    				 break;
    			 case "addApplication":
    				int b = dc.addApplication(params[i][0], params[i][1]);
    				res = ""+b;
    				 break;
    			 case "getApplications":
    				 List<Integer> x = dc.getApplications(params[i][0]);
    				 res = x.toString();
    				 break;
    			 case "stopApplication":
    				 dc.stopApplication(params[i][0]);
    				 break;
    			 case "removeMachine":
    				 dc.removeMachine(params[i][0]);
    				 break;
    		 }
    		 i++;
    		 System.out.print(res+",");
    	 }
     }
}

/*
 [null,null,[],null,[],[],null,[],null,[],[],null,null,54533,null,null,[],[78014],43462,null,null,[78014,92855],[],[],-1,null,null,null,43462,[78014,92855,95319],null,null,37053,[42444],null,null,[],[],null,null,null,55754,[96466],[],[],[],[96466],[],[96466],[96466],null,[],-1,[],[],-1,null,49468,[],[]]
 */
