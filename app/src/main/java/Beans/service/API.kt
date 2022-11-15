package Beans.service

import Beans.service.`class`.*
import retrofit2.Call
import retrofit2.http.*

interface API {
     @GET
     fun getCases(@Url url:String): Call<List<CasesResponse>>
     @GET
     fun getSubscriptions(@Url url:String): Call<List<SubscriptionResponse>>
     @GET
     fun getNotifications(@Url url:String): Call<List<NotificationResponse>>
     @GET
     fun getLawyers(@Url url:String): Call<List<PersonResponse>>
     @GET
     fun getOneCase(@Url url: String): Call<OneCaseReponse>

     @GET
     fun login(@Url url: String):Call<PersonResponse>
     @GET
     fun getOneLawyer(@Url url:String): Call<Lawyer>

     @GET
     fun getUserData(@Url url:String): Call<Client>

     @POST
     fun createPerson(@Url url:String, @Body client: RegisterClient): Call<RegisterClient>

     @POST
     fun createLaywer(@Url url:String, @Body lawyer: RegisterLawyer): Call<RegisterLawyer>

     @Headers("Content-Type: application/json")
     @PUT
     fun updateUserData(@Url url:String, @Body client: Client): Call<Client>

     @Headers("Content-Type: application/json")
     @POST("api/v1/consults")
     fun createCase(@Body caso: CASOPOST): Call<CASOPOST>

     @GET
     fun getMessageConsult(@Url url:String): Call<List<MessageResponse>>


     @Headers("Content-Type: application/json")
     @POST
     fun createMessage(@Url url:String,@Body message: MessageBody): Call<MessageResponse>

     @Headers("Content-Type: application/json")
     @POST
     fun createNotification(@Url url:String,@Body notification: NotificationBody): Call<NotificationResponse>
     @Headers("Content-Type: application/json")
     @PUT
     fun updateCases(@Url url:String,@Body case: CaseBody): Call<OneCaseReponse>
}