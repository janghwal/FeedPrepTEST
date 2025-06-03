package com.example.feedprep.domain.feedbackrequest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.domain.document.entity.Document;
import com.example.feedprep.domain.document.repository.DocumentRepository;
import com.example.feedprep.domain.feedbackrequestentity.common.RequestState;
import com.example.feedprep.domain.feedbackrequestentity.dto.request.FeedbackRequestDto;
import com.example.feedprep.domain.feedbackrequestentity.dto.response.FeedbackRequestEntityResponseDto;
import com.example.feedprep.domain.feedbackrequestentity.entity.FeedbackRequestEntity;
import com.example.feedprep.domain.feedbackrequestentity.repository.FeedbackRequestEntityRepository;
import com.example.feedprep.domain.feedbackrequestentity.service.FeedbackRequestService;
import com.example.feedprep.domain.user.entity.User;
import com.example.feedprep.domain.user.enums.UserRole;
import com.example.feedprep.domain.user.repository.UserRepository;

@ActiveProfiles("local")
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FeedbackRequestServiceTest {
	@Autowired
	private FeedbackRequestEntityRepository feedbackRequestEntityRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DocumentRepository documentRepository;
	@Autowired
	private FeedbackRequestService feedbackService;

	public List<User> UserSetting (){
		return List.of(
			 new User("Test", "Test@naver.com", "tester1234", UserRole.ADMIN),
			 new User("Astra1", "Test1@naver.com", "tester1234", UserRole.APPROVED_TUTOR),
			 new User("Astra2", "Test2@naver.com", "tester1234", UserRole.APPROVED_TUTOR),
			 new User("Astra3", "Test3@naver.com", "tester1234", UserRole.APPROVED_TUTOR),
			 new User("Astra4", "Test4@naver.com", "tester1234", UserRole.APPROVED_TUTOR),
			 new User("paragon", "Test3@naver.com", "tester1234", UserRole.STUDENT)
		 );
	}
	@Transactional
	@Test
	public void 요청하기(){

		List<User> users =UserSetting ();
		userRepository.saveAll(users );

		Document doc = new Document(users.get(5), "api/ef/?");
		documentRepository.save(doc);

		FeedbackRequestDto requestDto = new FeedbackRequestDto(3L, 1L, "Text");

		//중복 방지 테스트 생성되는지 확인할려면 주석 처리.
		// FeedbackRequestEntity alreadyExistReuest = new FeedbackRequestEntity(requestDto, users.get(5), users.get(2),doc);
		// alreadyExistReuest.updateRequestState(RequestState.PENDING);
        // feedbackRequestEntityRepository.save(alreadyExistReuest);

		long start = System.currentTimeMillis();
		FeedbackRequestEntityResponseDto feedbackRequestEntityResponseDto =
			feedbackService.saveRequest(requestDto, users.get(5).getUserId());
		long end= System.currentTimeMillis();
		System.out.println("첫 실행 시간: " + (end - start) + "ms"); // DB 조회

		System.out.println( " " + feedbackRequestEntityResponseDto.getId() +
			                "\n" + feedbackRequestEntityResponseDto.getTutorId() +
			                "\n" +feedbackRequestEntityResponseDto.getDocumentId() +
			                "\n" +feedbackRequestEntityResponseDto.getRequestState().toString()
		);
	 }

	@Transactional
	@Test
	public  void 요청_수정하기(){
		List<User> users =UserSetting ();
		userRepository.saveAll(users);

		Document doc = new Document(users.get(5), "api/ef/?");
		documentRepository.save(doc);

		FeedbackRequestDto requestDto = new FeedbackRequestDto(1L, 1L, "Text");
		FeedbackRequestEntityResponseDto feedbackRequestEntityResponseDto =  feedbackService.saveRequest(requestDto, users.get(5).getUserId());

		FeedbackRequestDto UpdateRequestDto = new FeedbackRequestDto(1L, 1L, "거절 한다.");
		long start = System.currentTimeMillis();
		FeedbackRequestEntityResponseDto UpdateFeedbackRequestEntityResponseDto =feedbackService.updateRequest(UpdateRequestDto,1L, users.get(5).getUserId());
		long end= System.currentTimeMillis();
		System.out.println("수정 작업 실행 시간: " + (end - start) + "ms"); // DB 조회

		System.out.println(UpdateFeedbackRequestEntityResponseDto.getId());
		System.out.println(UpdateFeedbackRequestEntityResponseDto.getTutorId());
		System.out.println(UpdateFeedbackRequestEntityResponseDto.getDocumentId());
		System.out.println(UpdateFeedbackRequestEntityResponseDto.getContent());
		System.out.println(UpdateFeedbackRequestEntityResponseDto.getRequestState().toString());
	}

	@Transactional
	@Test
	public void 요청_취소하기(){
		List<User> users =UserSetting ();
		userRepository.saveAll(users);

		Document doc = new Document(users.get(5), "api/ef/?");
		documentRepository.save(doc);

		FeedbackRequestDto requestDto = new FeedbackRequestDto(1L, 1L, "Text");
		feedbackService.saveRequest(requestDto, users.get(5).getUserId());

		long start = System.currentTimeMillis();
		ApiResponseDto CanCELEResponseDto =  feedbackService.cancleRequest(1L,users.get(5).getUserId());
		long end= System.currentTimeMillis();
		System.out.println("수정 작업 실행 시간: " + (end - start) + "ms"); // DB 조회

		System.out.println(CanCELEResponseDto.statusCode());
		System.out.println(CanCELEResponseDto.message());
	}

	@Transactional
	@Test
	public void 유저_아이디_기본_조회테스트() {

		List<User> users = UserSetting();
		userRepository.saveAll(users);

		Document doc = new Document(users.get(5), "api/ef/?");
		documentRepository.save(doc);
		List<FeedbackRequestDto> requestDtos = List.of(
			new FeedbackRequestDto(1L, 1L, "Text"),
			new FeedbackRequestDto(2L, 1L, "Text"),
			new FeedbackRequestDto(3L, 1L, "Text"),
			new FeedbackRequestDto(4L, 1L, "Text")

		);
		for(int  i =0; i <requestDtos.size(); i++){
			feedbackService.saveRequest(requestDtos.get(i), users.get(5).getUserId());
		}
		long start = System.currentTimeMillis();
		List<FeedbackRequestEntityResponseDto> getRequests = feedbackService.getRequest(users.get(5).getUserId(), null, null, null, 0, 20);
		long end= System.currentTimeMillis();
		System.out.println("수정 작업 실행 시간: " + (end - start) + "ms"); // DB 조회

		for (int i = 0; i < getRequests.size(); i++) {
                 System.out.println("" + getRequests.get(i).getTutorId() + " " + getRequests.get(i).getContent() +" " + getRequests.get(i).getRequestState() );
		}
	}

	@Transactional
	@Test
	public void 유저_아이디와_튜터아이디_기본_조회테스트() {

		List<User> users = UserSetting();
		userRepository.saveAll(users);

		Document doc = new Document(users.get(5), "api/ef/?");
		documentRepository.save(doc);
		List<FeedbackRequestDto> requestDtos = List.of(
			new FeedbackRequestDto(1L, 1L, "Text"),
			new FeedbackRequestDto(2L, 1L, "Text"),
			new FeedbackRequestDto(3L, 1L, "Text"),
			new FeedbackRequestDto(4L, 1L, "Text")

		);
		for(int  i =0; i <requestDtos.size(); i++){
			feedbackService.saveRequest(requestDtos.get(i), users.get(5).getUserId());
		}
		long start = System.currentTimeMillis();
		List<FeedbackRequestEntityResponseDto> getRequests = feedbackService.getRequest(users.get(5).getUserId(),3L, null, null, 0, 20);
		long end= System.currentTimeMillis();
		System.out.println("수정 작업 실행 시간: " + (end - start) + "ms"); // DB 조회

		for (int i = 0; i < getRequests.size(); i++) {
			System.out.println("" + getRequests.get(i).getTutorId() + " " + getRequests.get(i).getContent() +" " + getRequests.get(i).getRequestState() );
		}
	}

	@Transactional
	@Test
	public void 유저_아이디와_문서아이디_기본_조회테스트() {

		List<User> users = UserSetting();
		userRepository.saveAll(users);

		Document doc = new Document(users.get(5), "api/ef/?");
		documentRepository.save(doc);
		List<FeedbackRequestDto> requestDtos = List.of(
			new FeedbackRequestDto(1L, 1L, "Text"),
			new FeedbackRequestDto(2L, 1L, "Text"),
			new FeedbackRequestDto(3L, 1L, "Text"),
			new FeedbackRequestDto(4L, 1L, "Text")

		);
		for(int  i =0; i <requestDtos.size(); i++){
			feedbackService.saveRequest(requestDtos.get(i), users.get(5).getUserId());
		}
		long start = System.currentTimeMillis();
		List<FeedbackRequestEntityResponseDto> getRequests = feedbackService.getRequest(users.get(5).getUserId(),null, 1L, null, 0, 20);
		long end= System.currentTimeMillis();
		System.out.println("수정 작업 실행 시간: " + (end - start) + "ms"); // DB 조회

		for (int i = 0; i < getRequests.size(); i++) {
			System.out.println("" + getRequests.get(i).getTutorId() + " " + getRequests.get(i).getContent() +" " + getRequests.get(i).getRequestState() );
		}
	}
	@Transactional
	@Test
	public void 유저_아이디와_어느_조건_상관없이_Cancele제외_기본_조회테스트() {

		List<User> users = UserSetting();
		userRepository.saveAll(users);

		Document doc = new Document(users.get(5), "api/ef/?");
		documentRepository.save(doc);
		FeedbackRequestDto canceledRequestDto = new FeedbackRequestDto(3L, 1L, "Text");
		FeedbackRequestEntity alreadyExistReuest = new FeedbackRequestEntity(canceledRequestDto, users.get(5), users.get(2),doc);
		alreadyExistReuest.updateRequestState(RequestState.CANCELED);
		feedbackRequestEntityRepository.save(alreadyExistReuest);

		List<FeedbackRequestDto> requestDtos = List.of(
			new FeedbackRequestDto(1L, 1L, "Text"),
			new FeedbackRequestDto(2L, 1L, "Text"),
			new FeedbackRequestDto(3L, 1L, "Text"),
			new FeedbackRequestDto(4L, 1L, "Text")

		);

		for(int  i =0; i <requestDtos.size(); i++){
			feedbackService.saveRequest(requestDtos.get(i), users.get(5).getUserId());
		}
		long start = System.currentTimeMillis();
		List<FeedbackRequestEntityResponseDto> getRequests = feedbackService.getRequest(users.get(5).getUserId(),null, 1L, null, 0, 20);
		long end= System.currentTimeMillis();
		System.out.println("수정 작업 실행 시간: " + (end - start) + "ms"); // DB 조회

		for (int i = 0; i < getRequests.size(); i++) {
			System.out.println("" + getRequests.get(i).getTutorId() + " " + getRequests.get(i).getContent() +" " + getRequests.get(i).getRequestState() );
		}
	}
}
