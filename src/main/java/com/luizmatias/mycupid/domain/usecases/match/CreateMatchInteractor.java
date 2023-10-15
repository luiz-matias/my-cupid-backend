package com.luizmatias.mycupid.domain.usecases.match;

import com.luizmatias.mycupid.domain.entities.Chat;
import com.luizmatias.mycupid.domain.entities.Match;
import com.luizmatias.mycupid.domain.entities.User;
import com.luizmatias.mycupid.domain.entities.UserType;
import com.luizmatias.mycupid.domain.exceptions.FailedToSendNotificationException;
import com.luizmatias.mycupid.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.mycupid.domain.repositories.NotificationSenderRepository;
import com.luizmatias.mycupid.domain.repositories.MatchRepository;
import com.luizmatias.mycupid.domain.usecases.chat.CreateChatInteractor;
import com.luizmatias.mycupid.domain.usecases.like.RemoveLikeInteractor;

import java.util.Date;

public class CreateMatchInteractor {

    private final MatchRepository matchRepository;
    private final RemoveLikeInteractor removeLikeInteractor;
    private final CreateChatInteractor createChatInteractor;
    private final NotificationSenderRepository notificationSenderRepository;

    public CreateMatchInteractor(MatchRepository matchRepository, RemoveLikeInteractor removeLikeInteractor, CreateChatInteractor createChatInteractor, NotificationSenderRepository notificationSenderRepository) {
        this.matchRepository = matchRepository;
        this.removeLikeInteractor = removeLikeInteractor;
        this.createChatInteractor = createChatInteractor;
        this.notificationSenderRepository = notificationSenderRepository;
    }

    public Match createMatch(User from, User to) throws ResourceNotFoundException, FailedToSendNotificationException {
        User clientUser = from.getUserType() == UserType.CLIENT ? from : to;
        User developerUser = from.getUserType() == UserType.DEVELOPER ? from : to;

        Match match = new Match(
                null,
                clientUser,
                developerUser,
                new Date(),
                null
        );

        removeLikeInteractor.removeLike(from, to.getId());
        removeLikeInteractor.removeLike(to, from.getId());

        Chat chat = new Chat();
        chat.setCreatedAt(new Date());
        chat.setFirstUser(match.getClientUser());
        chat.setSecondUser(match.getDeveloperUser());

        createChatInteractor.createChat(chat);

        Match savedMatch = matchRepository.createMatch(match);

        notificationSenderRepository.sendMatchNotification(to.getEmail(), to.getFirstName(), from);

        return savedMatch;
    }

}
