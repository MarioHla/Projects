package DevOps404.Inventura.utills;

import org.springframework.stereotype.Service;

@Service
public class LocationUtill {
	
	
	public String getWerehouseByCoordinate(Coordiante c) {
		
		if(c.getX()<=45.837235 && c.getX()>=45.757426 && c.getY()>=15.831408 && c.getY()<15.954926)
			return "Skladište West";
		
		if(c.getX()<=45.837235 && c.getX()>=45.789135 && c.getY()>=15.954926 && c.getY()<16.000000)
			return "Skladište North";
		
		if(c.getX()<45.789135 && c.getX()>=45.757426 && c.getY()>=15.954926 && c.getY()<16.000000)
			return "Skladište South";
		
		if(c.getX()<=45.837235 && c.getX()>=45.757426 && c.getY()>=16.000000 && c.getY()<16.063959)
			return "Skladište East";
		
		return "unknown";
	}
	
}
