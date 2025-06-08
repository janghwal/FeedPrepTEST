package com.example.feedprep.domain.feedback;

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
import com.example.feedprep.domain.feedback.common.RejectReason;
import com.example.feedprep.domain.feedback.dto.request.FeedbackWriteRequestDto;
import com.example.feedprep.domain.feedback.dto.response.FeedbackRejectResponseDto;
import com.example.feedprep.domain.feedback.dto.response.FeedbackRequestListResponseDto;
import com.example.feedprep.domain.feedback.dto.response.FeedbackRequestResponseDto;
import com.example.feedprep.domain.feedback.dto.response.FeedbackResponseDto;
import com.example.feedprep.domain.feedback.service.FeedbackService;
import com.example.feedprep.domain.feedbackrequestentity.common.RequestState;
import com.example.feedprep.domain.feedbackrequestentity.dto.request.FeedbackRequestDto;
import com.example.feedprep.domain.feedbackrequestentity.entity.FeedbackRequestEntity;
import com.example.feedprep.domain.feedbackrequestentity.repository.FeedbackRequestEntityRepository;
import com.example.feedprep.domain.feedbackrequestentity.service.FeedbackRequestService;
import com.example.feedprep.domain.user.entity.User;
import com.example.feedprep.domain.user.enums.UserRole;
import com.example.feedprep.domain.user.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ActiveProfiles("local")
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FeedbackReviewServiceTest {

	@Autowired
	private FeedbackRequestEntityRepository feedbackRequestEntityRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DocumentRepository documentRepository;
	@Autowired
	private FeedbackRequestService feedbackRequestService;
	@Autowired
	private FeedbackService feedbackService;


	public List<User> tutorSetting (){
		return List.of(
			new User("Astra1", "Test1@naver.com", "tester1234", UserRole.APPROVED_TUTOR),
			new User("Astra2", "Test2@naver.com", "tester1234", UserRole.APPROVED_TUTOR),
			new User("Astra3", "Test3@naver.com", "tester1234", UserRole.APPROVED_TUTOR),
			new User("Astra4", "Test4@naver.com", "tester1234", UserRole.APPROVED_TUTOR)
		);
	}

	public List<User> userSetting (){
		return List.of(
			new User("paragon", "Test30@naver.com", "tester1234", UserRole.STUDENT),
			new User("paragon1", "Test5@naver.com", "tester1234", UserRole.STUDENT),
			new User("paragon2", "Test6@naver.com", "tester1234", UserRole.STUDENT),
			new User("paragon3", "Test7@naver.com", "tester1234", UserRole.STUDENT),
			new User("paragon4", "Test8@naver.com", "tester1234", UserRole.STUDENT)
		);
	}
	@Transactional
	@Test
	public void 단건_조회_테스트(){
		List<User> tutors =tutorSetting();
		List<User> users =userSetting ();
		userRepository.saveAll(tutors );
		userRepository.saveAll(users );

		Document doc = new Document(users.get(1), "api/ef/?");
		documentRepository.save(doc);
		List<FeedbackRequestDto> requestDtos = List.of(
			new FeedbackRequestDto(tutors.get(0).getUserId(), 1L, "Text"),
			new FeedbackRequestDto(tutors.get(1).getUserId(), 1L, "Text"),
			new FeedbackRequestDto(tutors.get(2).getUserId(), 1L, "Text"),
			new FeedbackRequestDto(tutors.get(3).getUserId(), 1L, "Text")

		);
		for(int  i =0; i <requestDtos.size(); i++){
			feedbackRequestService.createRequest( users.get(1).getUserId(), requestDtos.get(i));
		}
		long start = System.currentTimeMillis();
        FeedbackRequestResponseDto getRequest =  feedbackService.getFeedbackRequest(tutors.get(1).getUserId(),1L);
		long end= System.currentTimeMillis();
		System.out.println("수정 작업 실행 시간: " + (end - start) + "ms"); // DB 조회

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.enable(SerializationFeature.INDENT_OUTPUT); // 예쁘게 출력

		try {
			String json = mapper.writeValueAsString( getRequest); // 전체 객체를 JSON 변환
			System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	@Transactional
	@Test
	public void 다건_조회_테스트(){
		List<User> tutors =tutorSetting();
		List<User> users =userSetting ();
		userRepository.saveAll(tutors );
		userRepository.saveAll(users );

		Document doc = new Document(users.get(1), "api/ef/?");
		documentRepository.save(doc);
		List<FeedbackRequestDto> requestDtos = List.of(
			new FeedbackRequestDto(1L, 1L, "Text"),
			new FeedbackRequestDto(2L, 1L, "Text"),
			new FeedbackRequestDto(3L, 1L, "Text"),
			new FeedbackRequestDto(4L, 1L, "Text")

		);


		feedbackRequestService.createRequest( users.get(0).getUserId(), requestDtos.get(3));
		feedbackRequestService.createRequest( users.get(1).getUserId(), requestDtos.get(3));
		feedbackRequestService.createRequest( users.get(2).getUserId(), requestDtos.get(3));
		feedbackRequestService.createRequest( users.get(3).getUserId(), requestDtos.get(3));
		feedbackRequestService.createRequest( users.get(4).getUserId(), requestDtos.get(3));
		long start = System.currentTimeMillis();
		FeedbackRequestListResponseDto getRequests =  feedbackService.getFeedbackRequests(tutors.get(3).getUserId(),0, 20);
		long end= System.currentTimeMillis();
		System.out.println("수정 작업 실행 시간: " + (end - start) + "ms"); // DB 조회

		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT); // 예쁘게 출력

		try {
			String json = mapper.writeValueAsString(getRequests); // 전체 객체를 JSON 변환
			System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@Test
	public void 피드백_작성(){
		List<User> tutors =tutorSetting();
		List<User> users =userSetting ();
		userRepository.saveAll(tutors );
		userRepository.saveAll(users );

		Document doc = new Document(users.get(1), "api/ef/?");
		documentRepository.save(doc);

		FeedbackRequestDto testRequestDto = new FeedbackRequestDto(tutors.get(2).getUserId(), 1L, "Text");
		feedbackRequestService.createRequest( users.get(0).getUserId(), testRequestDto);

		FeedbackWriteRequestDto requestDto
			= new FeedbackWriteRequestDto(1L,"내용", null, null);

		long start = System.currentTimeMillis();
		FeedbackResponseDto response = feedbackService.createFeedback(tutors.get(2).getUserId(), 1L, requestDto);
		long end= System.currentTimeMillis();
		System.out.println("수정 작업 실행 시간: " + (end - start) + "ms"); // DB 조회

		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT); // 예쁘게 출력

		try {
			String json = mapper.writeValueAsString(response); // 전체 객체를 JSON 변환
			System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	@Transactional
	@Test
	public void 피드백_수정(){
		List<User> tutors =tutorSetting();
		List<User> users =userSetting ();
		userRepository.saveAll(tutors );
		userRepository.saveAll(users );

		Document doc = new Document(users.get(1), "api/ef/?");
		documentRepository.save(doc);

		//유저가 튜터4에게 피드백을 요청한다.
		FeedbackRequestDto testRequestDto =
			new FeedbackRequestDto(tutors.get(2).getUserId(), 1L, "Text");

		feedbackRequestService.createRequest( users.get(1).getUserId(), testRequestDto);

		//피드백 작성 하기
		FeedbackWriteRequestDto requestDto =
			new FeedbackWriteRequestDto(1L,"직성 완료했습니다.", null, null);

		//피드백 작성 완료 저장
		long start = System.currentTimeMillis();
		FeedbackResponseDto response = feedbackService.createFeedback(tutors.get(2).getUserId(), 1L, requestDto);
		long end= System.currentTimeMillis();
		System.out.println("수정 작업 실행 시간: " + (end - start) + "ms"); // DB 조회

		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT); // 예쁘게 출력

		try {
			String json = mapper.writeValueAsString(response); // 전체 객체를 JSON 변환
			System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@Test
	public void 피드백_작성_실패(){
		List<User> tutors =tutorSetting();
		List<User> users =userSetting ();
		userRepository.saveAll(tutors );
		userRepository.saveAll(users );

		Document doc = new Document(users.get(1), "api/ef/?");
		documentRepository.save(doc);

		//유저가 튜터4에게 피드백을 요청한다.
		FeedbackRequestDto testRequestDto = new FeedbackRequestDto(tutors.get(2).getUserId(), 1L, "Text");
		feedbackRequestService.createRequest( users.get(1).getUserId(), testRequestDto);

		//강제 Error 셋팅.
		FeedbackRequestEntity alreadyExistRequest = feedbackRequestEntityRepository.findById(1L).orElseThrow();
		alreadyExistRequest.updateRequestState(RequestState.CANCELED);

		//피드백 작성 하기

		FeedbackWriteRequestDto requestDto = new FeedbackWriteRequestDto(alreadyExistRequest.getId(),
			"직성 완료했습니다.", null, null);

		//피드백 작성 완료 저장
		long start = System.currentTimeMillis();
		FeedbackResponseDto response = feedbackService.createFeedback(tutors.get(2).getUserId(), 1L, requestDto);
		long end= System.currentTimeMillis();
		System.out.println("수정 작업 실행 시간: " + (end - start) + "ms"); // DB 조회

		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT); // 예쁘게 출력

		try {
			String json = mapper.writeValueAsString(response); // 전체 객체를 JSON 변환
			System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	@Transactional
	@Test
	public void 피드백_거절(){
		List<User> tutors =tutorSetting();
		List<User> users =userSetting ();
		userRepository.saveAll(tutors );
		userRepository.saveAll(users );

		Document doc = new Document(users.get(1), "api/ef/?");
		documentRepository.save(doc);

		//유저가 튜터4에게 피드백을 요청한다.
		FeedbackRequestDto testRequestDto = new FeedbackRequestDto(tutors.get(2).getUserId(), 1L, "Text");
		feedbackRequestService.createRequest( users.get(1).getUserId(), testRequestDto);
        //튜터는 특정 피드백 요청에 대해 거절 사유등록해서 거절을 승인한다.

		FeedbackWriteRequestDto feedbackWriteRequestDto =
			new FeedbackWriteRequestDto(1L, null, RejectReason.ETC, "OO 사유로 거절함.");

		long start = System.currentTimeMillis();
		ApiResponseDto response
			= feedbackService .rejectFeedbackRequest(tutors.get(2).getUserId(),1L,feedbackWriteRequestDto);
		long end= System.currentTimeMillis();
		System.out.println("수정 작업 실행 시간: " + (end - start) + "ms"); // DB 조회

		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT); // 예쁘게 출력

		try {
			String json = mapper.writeValueAsString(response); // 전체 객체를 JSON 변환
			System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
