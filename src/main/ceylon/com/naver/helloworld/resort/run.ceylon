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
		Guest(1, "jsh", "naver", 15),
		Guest(2, "hny", "daum", 10),
		Guest(3, "chy", "naver", 5)
	);
	value service = CeylonResort(repository);
	value names = service.findGuestNamesOfCompany("naver");
	print(names);
}