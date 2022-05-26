package com.Grandeur.GranduerBackend.registrationServices;

import com.Grandeur.GranduerBackend.DTOmodels.NameDTO;
import com.Grandeur.GranduerBackend.clientRegistrationRequestModel.RegistrationRequest;
import com.Grandeur.GranduerBackend.emailService.EmailSender;
import com.Grandeur.GranduerBackend.emailService.EmailValidator;
import com.Grandeur.GranduerBackend.exceptions.InvalidEmailException;
import com.Grandeur.GranduerBackend.modelEnums.ClientRole;
import com.Grandeur.GranduerBackend.models.Client;
import com.Grandeur.GranduerBackend.models.ConfirmationToken;
import com.Grandeur.GranduerBackend.services.serviceImplementations.ClientServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
@AllArgsConstructor
public class EmailRegistrationService {

    private final EmailValidator emailValidator;

    private final ClientServiceImpl clientServiceImpl;

    private final ConfirmationTokenService confirmationTokenService;

    private final EmailSender emailSender;




    public NameDTO register(RegistrationRequest request) {
        boolean iseValidEmail = emailValidator.test(request.getEmail());

        if(!iseValidEmail){
            throw new InvalidEmailException("The email "+request.getEmail()+" is invalid");
        }
        String token = clientServiceImpl.signUpClient(
                new Client(
                        request.getName(),
                        request.getEmail(),
                        request.getPassword(),
                        ClientRole.CLIENT
                )
        );
        String email = request.getEmail();
        String confirmationLink = "http://localhost:8090/api/v1/registration/confirm?token="+token;

        Thread thread = new Thread(()->{
            emailSender.send(request.getEmail(),
                    buildEmail(request.getName(), confirmationLink)
            );
        });
        thread.start();


        Optional<Client> client = clientServiceImpl.getClientByEmail(request.getEmail());

        if(client.isPresent()) {
            return new NameDTO(client.get().getName(), client.get().getEmail());
        }
        else{
            throw new UsernameNotFoundException("Did not find any user with the email"+request.getEmail());
        }
    }

    @Transactional
    public String confirmToken(String token){

        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() -> new IllegalStateException("token not found"));

        if(confirmationToken.getConfirmedAt() != null){
            throw new IllegalStateException("Email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if(expiredAt.isBefore(LocalDateTime.now())){
            confirmationTokenService.deleteConfirmationToken(token);
            return "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "  <meta charset=\"UTF-8\">\n" +
                    "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "  <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                    "  <link href=\"https://fonts.googleapis.com/css2?family=Poppins&display=swap\" rel=\"stylesheet\">\n" +
                    "  <title>Email Confirmation Error</title>\n" +
                    "  <style>\n" +
                    "    * {\n" +
                    "      font-family: 'Poppins', sans-serif;\n" +
                    "      padding: 5px;\n" +
                    "      text-align: center;\n" +
                    "    }\n" +
                    "  </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "  <div class=\"container\">\n" +
                    "    <h1>OOPS! Token Has Expired Please Try Signing Again</h1>\n" +
                    "    <p>Signup Link : <a href=\"http://127.0.0.1:5501/sign-up/signUp.html\">Grandeur-Home</a></p>\n" +
                    "  </div>\n" +
                    "</body>\n" +
                    "</html>";
        }

        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                "  <link href=\"https://fonts.googleapis.com/css2?family=Poppins&display=swap\" rel=\"stylesheet\">\n" +
                "  <title>Email Confirmation</title>\n" +
                "  <style>\n" +
                "    * {\n" +
                "      font-family: 'Poppins', sans-serif;\n" +
                "      padding: 5px;\n" +
                "      text-align: center;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div class=\"container\">\n" +
                "    <h1>THANK YOU! Your Account is verified...</h1>\n" +
                "    <p>Continue to our website<a href=\"http://127.0.0.1:5501/home/home.html\">Grandeur-Signup</a></p>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>";
    }


    private String buildEmail(String name, String link) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <link rel=\"stylesheet\" href=\"./email.css\">\n" +
                "  <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
                "  <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                "  <link href=\"https://fonts.googleapis.com/css2?family=Poppins&display=swap\" rel=\"stylesheet\">\n" +
                "  <script src=\"./email.js\"></script>\n" +
                "  <title>Document</title>\n" +
                "  <style>\n" +
                "    * {\n" +
                "  font-family: 'Poppins', sans-serif;\n" +
                "  padding: 10px;\n" +
                "}\n" +
                "\n" +
                "#about-us-image {\n" +
                "  margin: 0 auto;\n" +
                "  background-color: #33423b;\n" +
                "  width: 200px;\n" +
                "  height: 200px;\n" +
                "  border-radius: 50%;\n" +
                "  position: relative;\n" +
                "}\n" +
                "\n" +
                "div > .lead {\n" +
                "  position: absolute;\n" +
                "  left: 15%;\n" +
                "  top: 38%;\n" +
                "  font-size: 1.5rem;\n" +
                "  font-weight: bold;\n" +
                "  letter-spacing: 0.1rem;\n" +
                "  color: ghostwhite;\n" +
                "  /* translate: middle; */\n" +
                "}\n" +
                "  .verify {\n" +
                "    padding: 10px 20px;\n" +
                "    font-size: 1rem;\n" +
                "    background-color: #343a40;\n" +
                "    border-radius: 25px;\n" +
                "    border: none;\n" +
                "    cursor: pointer;\n" +
                "  }\n" +
                "\n" +
                "  .verify:hover {\n" +
                "    background-color: #495057;\n" +
                "  }\n" +
                "\n" +
                "  .verify a {\n" +
                "    color: white;\n" +
                "  }\n" +
                "\n" +
                "  .container {\n" +
                "    /* border: 1px solid black; */\n" +
                "    background-color: #dee2e6;\n" +
                "    border-radius: 20px;\n" +
                "    text-align: center;\n" +
                "    width: 100vh;\n" +
                "    margin: 0 auto;\n" +
                "  }\n" +
                "\n" +
                "  section h4 {\n" +
                "    text-align: left;\n" +
                "    font-size: 1.5em;\n" +
                "    margin-left: 20px;\n" +
                "  }\n" +
                "  \n" +
                "  .header-row {\n" +
                "    background-color: #000;\n" +
                "    color: white;\n" +
                "    border-top-left-radius: 20px;\n" +
                "    border-top-right-radius: 20px;\n" +
                "    box-shadow: 0px 5px 10px 0px rgba(0,0,0,0.75);\n" +
                "-webkit-box-shadow: 0px 5px 10px 0px rgba(0,0,0,0.75);\n" +
                "-moz-box-shadow: 0px 5px 10px 0px rgba(0,0,0,0.75);\n" +
                "    /* justify-content: space-between; */\n" +
                "  }\n" +
                "\n" +
                "  #heading {\n" +
                "    text-align: center;\n" +
                "  }\n" +
                "  #confirm-statement {\n" +
                "    text-align: left;\n" +
                "  }\n" +
                "  </style>\n" +
                "\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div class=\"container\">\n" +
                "\n" +
                "    <!-- <div class=\"header-row\">\n" +
                "      <div>\n" +
                "        <h1>GRANDEUR</h1>\n" +
                "      </div>\n" +
                "    <div>\n" +
                "      <h1>CONFIRM YOUR E-MAIL</h1>\n" +
                "    </div>\n" +
                "    </div> -->\n" +
                "\n" +
                "  <div class=\"header-row\">\n" +
                "    <div>\n" +
                "      <h1 id=\"heading\">GRANDEUR</h1>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "\n" +
                "  <div id=\"confirm\">\n" +
                "    <h1 id=\"confirm-statement\">CONFIRM YOUR E-MAIL</h1>\n" +
                "  </div>\n" +
                "\n" +
                "  <section id=\"section-handle\">\n" +
                "    <h4>Hi "+name+",</h4>\n" +
                "    <p>Thank you for Signing up with Grandeur. To start exploring Grandeur,</p>\n" +
                "    <p>Please confirm your email address by clicking the link below.</p>\n" +
                "    <button class=\"verify\"><a href="+link+" style=\"text-decoration: none\">Verify Now</a></button>\n" +
                "  </section>\n" +
                "\n" +
                "  <footer>\n" +
                "    <h4>The link will expire in 15 minutes.</h5>\n" +
                "    <h4>This is an auto generated Email, Please do not reply to this mail.</h4>\n" +
                "  </footer>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>";
    }
}
