import com.naver.helloworld.resort.domain {
	Guest
}
import com.naver.helloworld.resort.repository {
	MemoryRepository
}
import com.naver.helloworld.resort.service {
	CeylonResort
}

"Run the module `com.naver.helloworld.resort`."
shared void run() {

	value repository  = MemoryRepository();
	repository.save(
		Guest(1, "jsh", "Naver", 15),
		Guest(2, "hny", "Line", 10),
		Guest(3, "chy", "Naver", 5)
	);
	value service = CeylonResort(repository);
	value names = service.findGuestNamesByCompany("Naver");
	print(names);
}
