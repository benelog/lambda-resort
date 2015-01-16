import com.naver.helloworld.resort.domain {Guest}
import com.naver.helloworld.resort.repository {GuestRepository}
import ceylon.interop.java { CeylonIterable }
import java.util {JList = List, JArrayList = ArrayList }
import java.lang {JString = String}

shared class CeylonResort (GuestRepository repository)	satisfies ResortService {
	shared actual JList<JString> findGuestNamesByCompany(String company) {
		value all = repository.findAll() ;
		value names = CeylonIterable(all)
			.filter((Guest g) => g.company == company)
			.sort(byIncreasing((Guest g) => g.grade.intValue()))
			.map((Guest g) => g.name);
		
		value jnames = JArrayList<JString>();
		for (name in names) {jnames.add(JString(name));}
		return jnames;
	}
}
