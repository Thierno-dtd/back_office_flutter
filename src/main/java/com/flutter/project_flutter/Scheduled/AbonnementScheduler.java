package com.flutter.project_flutter.Scheduled;

import com.flutter.project_flutter.constants.AbonnementStaut;
import com.flutter.project_flutter.entites.Abonnement;
import com.flutter.project_flutter.repositories.AbonnementRepository;
import com.flutter.project_flutter.servicesimpl.AbonnementServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class AbonnementScheduler {
    @Autowired
    private final AbonnementServices abonnementService;
    @Autowired
    private AbonnementRepository abonnementRepository;
    public AbonnementScheduler(AbonnementServices abonnementService) {
        this.abonnementService = abonnementService;
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void verifierAbonnements() {
        LocalDateTime now = LocalDateTime.now();

        List<Abonnement> abonnements = abonnementService.getAllAbonnementWhichWillBeExpire(now);
        for (Abonnement abonnement : abonnements) {
            abonnement.setStatut(AbonnementStaut.EXPIRE);
            abonnementRepository.save(abonnement);
        }
    }
}
