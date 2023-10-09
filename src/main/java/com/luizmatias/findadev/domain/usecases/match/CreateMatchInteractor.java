package com.luizmatias.findadev.domain.usecases.match;

import com.luizmatias.findadev.domain.entities.Chat;
import com.luizmatias.findadev.domain.entities.Match;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import com.luizmatias.findadev.domain.repositories.MatchRepository;
import com.luizmatias.findadev.domain.usecases.chat.CreateChatInteractor;
import com.luizmatias.findadev.domain.usecases.like.RemoveLikeInteractor;

import java.util.Date;

public class CreateMatchInteractor {

    private final MatchRepository matchRepository;
    private final RemoveLikeInteractor removeLikeInteractor;
    private final CreateChatInteractor createChatInteractor;

    public CreateMatchInteractor(MatchRepository matchRepository, RemoveLikeInteractor removeLikeInteractor, CreateChatInteractor createChatInteractor) {
        this.matchRepository = matchRepository;
        this.removeLikeInteractor = removeLikeInteractor;
        this.createChatInteractor = createChatInteractor;
    }

    public Match createMatch(Match match) throws ResourceNotFoundException {
        removeLikeInteractor.removeLike(match.getClientUser(), match.getDeveloperUser().getId());
        removeLikeInteractor.removeLike(match.getDeveloperUser(), match.getClientUser().getId());

        Chat chat = new Chat();
        chat.setCreatedAt(new Date());
        chat.setFirstUser(match.getClientUser());
        chat.setSecondUser(match.getDeveloperUser());

        createChatInteractor.createChat(chat);

        return matchRepository.createMatch(match);
    }

}
